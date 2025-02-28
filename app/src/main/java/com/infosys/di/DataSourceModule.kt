package com.infosys.di

import com.infosys.domain.datasource.DataSource
import com.infosys.domain.datasource.LocalDataSource
import com.infosys.domain.local.CartDao
import com.infosys.domain.remote.FoodService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataSourceModule {

    @Singleton
    @Provides
    fun provideDataSource(
        service: FoodService
    ): DataSource {
        return DataSource(service)
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(
        service: CartDao
    ): LocalDataSource {
        return LocalDataSource(service)
    }
}