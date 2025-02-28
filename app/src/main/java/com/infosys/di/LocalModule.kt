package com.infosys.di

import android.content.Context
import androidx.room.Room
import com.infosys.domain.local.AppDatabase
import com.infosys.domain.local.CartDao
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
}