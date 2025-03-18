package com.infosys.di

import com.infosys.data.localDatabase.dao.CartDao
import com.infosys.data.localDatabase.MyDataStore
import com.infosys.data.localDatabase.dao.CardDao
import com.infosys.data.localDatabase.dao.OrderDao
import com.infosys.data.remote.FoodService
import com.infosys.data.repositoryImpl.CartsDetailLocalRepositoryImpl
import com.infosys.data.repositoryImpl.AllCategoriesAndSubCategoriesRepositoryImpl
import com.infosys.data.repositoryImpl.CardsInfoRepositoryImpl
import com.infosys.data.repositoryImpl.MenuCartLocalRepositoryImpl
import com.infosys.data.repositoryImpl.OrdersLocalRepositoryImpl
import com.infosys.data.repositoryImpl.UserInfoLocalRepositoryImpl
import com.infosys.data.repositoryImpl.MenusAndSubCategoryDetailsRepositoryImpl
import com.infosys.domain.repository.CartsDetailLocalRepository
import com.infosys.domain.repository.AllCategoriesAndSubCategoriesRepository
import com.infosys.domain.repository.CardsInfoRepository
import com.infosys.domain.repository.MenuCartLocalRepository
import com.infosys.domain.repository.OrdersLocalRepository
import com.infosys.domain.repository.UserInfoLocalRepository
import com.infosys.domain.repository.MenusAndSubCategoryDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    fun provideAllCategoriesAndSubCategoriesRepository(
        service: FoodService
    ): AllCategoriesAndSubCategoriesRepository {
        return AllCategoriesAndSubCategoriesRepositoryImpl(service)
    }

    @Provides
    fun provideMenusAndSubCategoryDetailsRepository(
        service: FoodService
    ): MenusAndSubCategoryDetailsRepository {
        return MenusAndSubCategoryDetailsRepositoryImpl(service)
    }

    @Provides
    fun provideCartsDetailLocalRepository(
        service: CartDao
    ): CartsDetailLocalRepository {
        return CartsDetailLocalRepositoryImpl(service)
    }

    @Provides
    fun provideMenuCartLocalRepository(
        service: CartDao
    ): MenuCartLocalRepository {
        return MenuCartLocalRepositoryImpl(service)
    }

    @Provides
    fun provideOrdersLocalRepository(
        service: OrderDao
    ): OrdersLocalRepository {
        return OrdersLocalRepositoryImpl(service)
    }

    @Provides
    fun provideUserInfoLocalRepository(
        store: MyDataStore
    ): UserInfoLocalRepository {
        return UserInfoLocalRepositoryImpl(store)
    }

    @Provides
    fun provideCardsInfoRepository(
        cardDao: CardDao
    ): CardsInfoRepository {
        return CardsInfoRepositoryImpl(cardDao)
    }
}