package com.infosys.di

import com.infosys.data.datasource.AllCartItemsLocalDataSource
import com.infosys.data.datasource.AllCategoriesDataSource
import com.infosys.data.datasource.DeleteCartItemLocalDataSource
import com.infosys.data.datasource.FetchCartItemLocalDataSource
import com.infosys.data.datasource.InsertCartItemLocalDataSource
import com.infosys.data.datasource.MenuDataSource
import com.infosys.data.datasource.SubCategoriesDataSource
import com.infosys.data.datasource.SubCategoryDetailsDataSource
import com.infosys.data.datasource.UpdateCartItemLocalDataSource
import com.infosys.data.local.CartDao
import com.infosys.data.remote.FoodService
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
    fun provideAllCategoriesDataSource(
        service: FoodService
    ): AllCategoriesDataSource {
        return AllCategoriesDataSource(service)
    }

    @Singleton
    @Provides
    fun provideSubCategoriesDataSource(
        service: FoodService
    ): SubCategoriesDataSource {
        return SubCategoriesDataSource(service)
    }

    @Singleton
    @Provides
    fun provideSubCategoryDetailsDataSource(
        service: FoodService
    ): SubCategoryDetailsDataSource {
        return SubCategoryDetailsDataSource(service)
    }

    @Singleton
    @Provides
    fun provideMenuListDataSource(
        service: FoodService
    ): MenuDataSource {
        return MenuDataSource(service)
    }

    @Singleton
    @Provides
    fun provideAllCartItemsLocalDataSource(
        service: CartDao
    ): AllCartItemsLocalDataSource {
        return AllCartItemsLocalDataSource(service)
    }

    @Singleton
    @Provides
    fun provideFetchCartItemLocalDataSource(
        service: CartDao
    ): FetchCartItemLocalDataSource {
        return FetchCartItemLocalDataSource(service)
    }

    @Singleton
    @Provides
    fun provideInsertCartItemLocalDataSource(
        service: CartDao
    ): InsertCartItemLocalDataSource {
        return InsertCartItemLocalDataSource(service)
    }

    @Singleton
    @Provides
    fun provideUpdateCartItemLocalDataSource(
        service: CartDao
    ): UpdateCartItemLocalDataSource {
        return UpdateCartItemLocalDataSource(service)
    }

    @Singleton
    @Provides
    fun provideDeleteCartItemLocalDataSource(
        service: CartDao
    ): DeleteCartItemLocalDataSource {
        return DeleteCartItemLocalDataSource(service)
    }
}