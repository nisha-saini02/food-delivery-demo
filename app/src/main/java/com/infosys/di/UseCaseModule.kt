package com.infosys.di

import com.infosys.domain.repository.CartsDetailLocalRepository
import com.infosys.domain.repository.AllCategoriesAndSubCategoriesRepository
import com.infosys.domain.repository.MenuCartLocalRepository
import com.infosys.domain.repository.OrdersLocalRepository
import com.infosys.domain.repository.UserInfoLocalRepository
import com.infosys.domain.repository.MenusAndSubCategoryDetailsRepository
import com.infosys.domain.usecase.AllCartItemsLocalUseCase
import com.infosys.domain.usecase.AllCategoriesUseCase
import com.infosys.domain.usecase.ClearUserInfoLocalUseCase
import com.infosys.domain.usecase.CountCartItemsLocalUseCase
import com.infosys.domain.usecase.DeleteAllCartsLocalUseCase
import com.infosys.domain.usecase.DeleteCartLocalUseCase
import com.infosys.domain.usecase.FetchOrderLocalUserCase
import com.infosys.domain.usecase.GrandTotalCartItemsLocalUseCase
import com.infosys.domain.usecase.InsertCartItemLocalUseCase
import com.infosys.domain.usecase.InsertOrderItemLocalUseCase
import com.infosys.domain.usecase.MenuListUseCase
import com.infosys.domain.usecase.OrderListLocalUseCase
import com.infosys.domain.usecase.ReadUserInfoLocalUseCase
import com.infosys.domain.usecase.SubCategoriesUseCase
import com.infosys.domain.usecase.SubCategoryDetailsUseCase
import com.infosys.domain.usecase.UpdateCartItemLocalUseCase
import com.infosys.domain.usecase.WriteUserInfoLocalUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {

    @Provides
    fun provideAllCategoriesUseCase(
        repository: AllCategoriesAndSubCategoriesRepository
    ): AllCategoriesUseCase {
        return AllCategoriesUseCase(repository)
    }

    @Provides
    fun provideSubCategoriesUseCase(
        repository: AllCategoriesAndSubCategoriesRepository
    ): SubCategoriesUseCase {
        return SubCategoriesUseCase(repository)
    }

    @Provides
    fun provideSubCategoryDetailsUseCase(
        repository: MenusAndSubCategoryDetailsRepository
    ): SubCategoryDetailsUseCase {
        return SubCategoryDetailsUseCase(repository)
    }

    @Provides
    fun provideMenuListUseCase(
        repository: MenusAndSubCategoryDetailsRepository
    ): MenuListUseCase {
        return MenuListUseCase(repository)
    }

    @Provides
    fun provideAllCartItemsLocalUseCase(
        repository: CartsDetailLocalRepository
    ): AllCartItemsLocalUseCase {
        return AllCartItemsLocalUseCase(repository)
    }

    @Provides
    fun provideInsertCartItemLocalUseCase(
        repository: MenuCartLocalRepository
    ): InsertCartItemLocalUseCase {
        return InsertCartItemLocalUseCase(repository)
    }

    @Provides
    fun provideUpdateCartItemLocalUseCase(
        repository: MenuCartLocalRepository
    ): UpdateCartItemLocalUseCase {
        return UpdateCartItemLocalUseCase(repository)
    }

    @Provides
    fun provideDeleteCartItemLocalUseCase(
        repository: MenuCartLocalRepository
    ): DeleteCartLocalUseCase {
        return DeleteCartLocalUseCase(repository)
    }

    @Provides
    fun provideOrderListLocalUseCase(
        repository: OrdersLocalRepository
    ): OrderListLocalUseCase {
        return OrderListLocalUseCase(repository)
    }

    @Provides
    fun provideInsertOrderItemLocalUseCase(
        repository: OrdersLocalRepository
    ): InsertOrderItemLocalUseCase {
        return InsertOrderItemLocalUseCase(repository)
    }

    @Provides
    fun provideCountCartItemsLocalUseCase(
        repository: CartsDetailLocalRepository
    ): CountCartItemsLocalUseCase {
        return CountCartItemsLocalUseCase(repository)
    }

    @Provides
    fun provideGrandTotalCartItemsLocalUseCase(
        repository: CartsDetailLocalRepository
    ): GrandTotalCartItemsLocalUseCase {
        return GrandTotalCartItemsLocalUseCase(repository)
    }

    @Provides
    fun provideDeleteAllCartsLocalUseCase(
        repository: CartsDetailLocalRepository
    ): DeleteAllCartsLocalUseCase {
        return DeleteAllCartsLocalUseCase(repository)
    }

    @Provides
    fun provideFetchOrderLocalUserCase(
        repository: OrdersLocalRepository
    ): FetchOrderLocalUserCase {
        return FetchOrderLocalUserCase(repository)
    }

    @Provides
    fun provideReadUserInfoLocalUseCase(
        repository: UserInfoLocalRepository
    ): ReadUserInfoLocalUseCase {
        return ReadUserInfoLocalUseCase(repository)
    }

    @Provides
    fun provideWriteUserInfoLocalUseCase(
        repository: UserInfoLocalRepository
    ): WriteUserInfoLocalUseCase {
        return WriteUserInfoLocalUseCase(repository)
    }

    @Provides
    fun provideClearUserInfoLocalUseCase(
        repository: UserInfoLocalRepository
    ): ClearUserInfoLocalUseCase {
        return ClearUserInfoLocalUseCase(repository)
    }
}