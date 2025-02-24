package com.infosys.di

import com.infosys.BuildConfig
import com.google.gson.GsonBuilder
import com.infosys.domain.remote.FoodService
import com.infosys.domain.remote.NullOnEmptyConverterFactory
import com.infosys.utils.HttpUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .client(getRetrofitClient())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().disableHtmlEscaping().setExclusionStrategies()
                        .create()
                )
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthApiManger(retrofit: Retrofit): FoodService {
        return retrofit.create(FoodService::class.java)
    }

    private fun getRetrofitClient(): OkHttpClient {
        return HttpUtils.getInstance().unsafeOkHttpClientBuilder.apply {
            this.addInterceptor(Interceptor {
                val builder = it.request().newBuilder()
                it.proceed(builder.build())
            })
        }
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().build())
            }.also { client ->
                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }
                client.connectTimeout(2, TimeUnit.MINUTES)
                    .readTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(2, TimeUnit.MINUTES)
            }
            .build()
    }

}