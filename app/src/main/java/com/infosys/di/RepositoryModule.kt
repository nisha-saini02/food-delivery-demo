package com.infosys.di

import com.infosys.data.localDatabase.CartDao
import com.infosys.data.localDatabase.MyDataStore
import com.infosys.data.remote.FoodService
import com.infosys.data.repositoryImpl.AllCartItemsLocalRepositoryImpl
import com.infosys.data.repositoryImpl.AllCategoriesRepositoryImpl
import com.infosys.data.repositoryImpl.ClearUserInfoLocalRepositoryImpl
import com.infosys.data.repositoryImpl.DeleteCartItemLocalRepositoryImpl
import com.infosys.data.repositoryImpl.FetchCartItemLocalRepositoryImpl
import com.infosys.data.repositoryImpl.InsertCartItemLocalRepositoryImpl
import com.infosys.data.repositoryImpl.MenuListRepositoryImpl
import com.infosys.data.repositoryImpl.ReadUserInfoLocalRepositoryImpl
import com.infosys.data.repositoryImpl.SubCategoriesRepositoryImpl
import com.infosys.data.repositoryImpl.SubCategoryDetailsRepositoryImpl
import com.infosys.data.repositoryImpl.UpdateCartItemLocalRepositoryImpl
import com.infosys.data.repositoryImpl.WriteUserInfoLocalRepositoryImpl
import com.infosys.domain.repository.AllCartItemsLocalRepository
import com.infosys.domain.repository.AllCategoriesRepository
import com.infosys.domain.repository.ClearUserInfoLocalRepository
import com.infosys.domain.repository.DeleteCartItemLocalRepository
import com.infosys.domain.repository.FetchCartItemLocalRepository
import com.infosys.domain.repository.InsertCartItemLocalRepository
import com.infosys.domain.repository.MenuListRepository
import com.infosys.domain.repository.ReadUserInfoLocalRepository
import com.infosys.domain.repository.SubCategoriesRepository
import com.infosys.domain.repository.SubCategoryDetailsRepository
import com.infosys.domain.repository.UpdateCartItemLocalRepository
import com.infosys.domain.repository.WriteUserInfoLocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    fun provideAllCategoriesRepository(
        service: FoodService
    ): AllCategoriesRepository {
        return AllCategoriesRepositoryImpl(service)
    }

    @Provides
    fun provideSubCategoriesRepository(
        service: FoodService
    ): SubCategoriesRepository {
        return SubCategoriesRepositoryImpl(service)
    }
    
    @Provides
    fun provideSubCategoryDetailsRepository(
        service: FoodService
    ): SubCategoryDetailsRepository {
        return SubCategoryDetailsRepositoryImpl(service)
    }
    
    @Provides
    fun provideMenuRepository(
        service: FoodService
    ): MenuListRepository {
        return MenuListRepositoryImpl(service)
    }

    @Provides
    fun provideAllCartItemsLocalRepository(
        service: CartDao
    ): AllCartItemsLocalRepository {
        return AllCartItemsLocalRepositoryImpl(service)
    }

    @Provides
    fun provideFetchCartItemLocalRepository(
        service: CartDao
    ): FetchCartItemLocalRepository {
        return FetchCartItemLocalRepositoryImpl(service)
    }

    @Provides
    fun provideInsertCartItemLocalRepository(
        service: CartDao
    ): InsertCartItemLocalRepository {
        return InsertCartItemLocalRepositoryImpl(service)
    }

    @Provides
    fun provideUpdateCartItemLocalRepository(
        service: CartDao
    ): UpdateCartItemLocalRepository {
        return UpdateCartItemLocalRepositoryImpl(service)
    }

    @Provides
    fun provideDeleteCartItemLocalRepository(
        service: CartDao
    ): DeleteCartItemLocalRepository {
        return DeleteCartItemLocalRepositoryImpl(service)
    }

    @Provides
    fun provideReadUserInfoLocalRepository(
        store: MyDataStore
    ): ReadUserInfoLocalRepository {
        return ReadUserInfoLocalRepositoryImpl(store)
    }

    @Provides
    fun provideWriteUserInfoLocalRepository(
        store: MyDataStore
    ): WriteUserInfoLocalRepository {
        return WriteUserInfoLocalRepositoryImpl(store)
    }

    @Provides
    fun provideClearUserInfoLocalRepository(
        store: MyDataStore
    ): ClearUserInfoLocalRepository {
        return ClearUserInfoLocalRepositoryImpl(store)
    }
}