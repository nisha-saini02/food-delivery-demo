package com.infosys.di

import android.content.Context
import androidx.room.Room
import com.infosys.data.localDatabase.AppDatabase
import com.infosys.data.localDatabase.CartDao
import com.infosys.data.localDatabase.MyDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Singleton
    @Provides
    fun provideLocalDatabase(
        @ApplicationContext
        context: Context
    ): AppDatabase {
        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "shopping"
        ).build()

        return db
    }

    @Provides
    fun provideUserDao(database: AppDatabase): CartDao {
        return database.cartDao()
    }

    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext
        context: Context
    ): MyDataStore {
        return MyDataStore(context)
    }
}