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
import com.infosys.data.repositoryImpl.AllCartItemsLocalRepositoryImpl
import com.infosys.data.repositoryImpl.AllCategoriesRepositoryImpl
import com.infosys.data.repositoryImpl.DeleteCartItemLocalRepositoryImpl
import com.infosys.data.repositoryImpl.FetchCartItemLocalRepositoryImpl
import com.infosys.data.repositoryImpl.InsertCartItemLocalRepositoryImpl
import com.infosys.data.repositoryImpl.MenuListRepositoryImpl
import com.infosys.data.repositoryImpl.SubCategoriesRepositoryImpl
import com.infosys.data.repositoryImpl.SubCategoryDetailsRepositoryImpl
import com.infosys.data.repositoryImpl.UpdateCartItemLocalRepositoryImpl
import com.infosys.domain.repository.AllCartItemsLocalRepository
import com.infosys.domain.repository.AllCategoriesRepository
import com.infosys.domain.repository.DeleteCartItemLocalRepository
import com.infosys.domain.repository.FetchCartItemLocalRepository
import com.infosys.domain.repository.InsertCartItemLocalRepository
import com.infosys.domain.repository.MenuListRepository
import com.infosys.domain.repository.SubCategoriesRepository
import com.infosys.domain.repository.SubCategoryDetailsRepository
import com.infosys.domain.repository.UpdateCartItemLocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideAllCategoriesRepository(
        dataSource: AllCategoriesDataSource
    ): AllCategoriesRepository {
        return AllCategoriesRepositoryImpl(dataSource)
    }

    @Singleton
    @Provides
    fun provideSubCategoriesRepository(
        dataSource: SubCategoriesDataSource
    ): SubCategoriesRepository {
        return SubCategoriesRepositoryImpl(dataSource)
    }

    @Singleton
    @Provides
    fun provideSubCategoryDetailsRepository(
        dataSource: SubCategoryDetailsDataSource
    ): SubCategoryDetailsRepository {
        return SubCategoryDetailsRepositoryImpl(dataSource)
    }

    @Singleton
    @Provides
    fun provideMenuRepository(
        dataSource: MenuDataSource
    ): MenuListRepository {
        return MenuListRepositoryImpl(dataSource)
    }

    @Singleton
    @Provides
    fun provideAllCartItemsLocalRepository(
        dataSource: AllCartItemsLocalDataSource
    ): AllCartItemsLocalRepository {
        return AllCartItemsLocalRepositoryImpl(dataSource)
    }

    @Singleton
    @Provides
    fun provideFetchCartItemLocalRepository(
        dataSource: FetchCartItemLocalDataSource
    ): FetchCartItemLocalRepository {
        return FetchCartItemLocalRepositoryImpl(dataSource)
    }

    @Singleton
    @Provides
    fun provideInsertCartItemLocalRepository(
        dataSource: InsertCartItemLocalDataSource
    ): InsertCartItemLocalRepository {
        return InsertCartItemLocalRepositoryImpl(dataSource)
    }

    @Singleton
    @Provides
    fun provideUpdateCartItemLocalRepository(
        dataSource: UpdateCartItemLocalDataSource
    ): UpdateCartItemLocalRepository {
        return UpdateCartItemLocalRepositoryImpl(dataSource)
    }

    @Singleton
    @Provides
    fun provideDeleteCartItemLocalRepository(
        dataSource: DeleteCartItemLocalDataSource
    ): DeleteCartItemLocalRepository {
        return DeleteCartItemLocalRepositoryImpl(dataSource)
    }
}