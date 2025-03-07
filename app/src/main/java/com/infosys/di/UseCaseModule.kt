package com.infosys.di

import com.infosys.data.model.usecase.AuthUseCase
import com.infosys.data.model.usecase.LocalUseCase
import com.infosys.data.model.usecase.RemoteUseCase
import com.infosys.domain.repository.AllCartItemsLocalRepository
import com.infosys.domain.repository.AllCategoriesRepository
import com.infosys.domain.repository.ClearUserInfoLocalRepository
import com.infosys.domain.repository.DeleteCartItemLocalRepository
import com.infosys.domain.repository.FetchCartItemLocalRepository
import com.infosys.domain.repository.InsertCartItemLocalRepository
import com.infosys.domain.repository.MenuListRepository
import com.infosys.domain.repository.OrderListLocalRepository
import com.infosys.domain.repository.ReadUserInfoLocalRepository
import com.infosys.domain.repository.SubCategoriesRepository
import com.infosys.domain.repository.SubCategoryDetailsRepository
import com.infosys.domain.repository.UpdateCartItemLocalRepository
import com.infosys.domain.repository.WriteUserInfoLocalRepository
import com.infosys.domain.usecase.AllCartItemsLocalUseCase
import com.infosys.domain.usecase.AllCategoriesUseCase
import com.infosys.domain.usecase.ClearUserInfoLocalUseCase
import com.infosys.domain.usecase.DeleteCartItemLocalUseCase
import com.infosys.domain.usecase.FetchCartItemLocalUseCase
import com.infosys.domain.usecase.InsertCartItemLocalUseCase
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
        repository: AllCategoriesRepository
    ): AllCategoriesUseCase {
        return AllCategoriesUseCase(repository)
    }

    @Provides
    fun provideSubCategoriesUseCase(
        repository: SubCategoriesRepository
    ): SubCategoriesUseCase {
        return SubCategoriesUseCase(repository)
    }

    @Provides
    fun provideSubCategoryDetailsUseCase(
        repository: SubCategoryDetailsRepository
    ): SubCategoryDetailsUseCase {
        return SubCategoryDetailsUseCase(repository)
    }

    @Provides
    fun provideMenuListUseCase(
        repository: MenuListRepository
    ): MenuListUseCase {
        return MenuListUseCase(repository)
    }

    @Provides
    fun provideAllCartItemsLocalUseCase(
        repository: AllCartItemsLocalRepository
    ): AllCartItemsLocalUseCase {
        return AllCartItemsLocalUseCase(repository)
    }

    @Provides
    fun provideFetchCartItemLocalUseCase(
        repository: FetchCartItemLocalRepository
    ): FetchCartItemLocalUseCase {
        return FetchCartItemLocalUseCase(repository)
    }

    @Provides
    fun provideInsertCartItemLocalUseCase(
        repository: InsertCartItemLocalRepository
    ): InsertCartItemLocalUseCase {
        return InsertCartItemLocalUseCase(repository)
    }

    @Provides
    fun provideUpdateCartItemLocalUseCase(
        repository: UpdateCartItemLocalRepository
    ): UpdateCartItemLocalUseCase {
        return UpdateCartItemLocalUseCase(repository)
    }

    @Provides
    fun provideDeleteCartItemLocalUseCase(
        repository: DeleteCartItemLocalRepository
    ): DeleteCartItemLocalUseCase {
        return DeleteCartItemLocalUseCase(repository)
    }

    @Provides
    fun provideOrderListLocalUseCase(
        repository: OrderListLocalRepository
    ): OrderListLocalUseCase {
        return OrderListLocalUseCase(repository)
    }

    @Provides
    fun provideLocalUseCase(
        allCartItemsLocalUseCase: AllCartItemsLocalUseCase,
        fetchCartItemLocalUseCase: FetchCartItemLocalUseCase,
        insertCartItemLocalUseCase: InsertCartItemLocalUseCase,
        updateCartItemLocalUseCase: UpdateCartItemLocalUseCase,
        deleteCartItemLocalUseCase: DeleteCartItemLocalUseCase,
        orderListLocalUseCase: OrderListLocalUseCase,
    ) = LocalUseCase(allCartItemsLocalUseCase, fetchCartItemLocalUseCase, insertCartItemLocalUseCase, updateCartItemLocalUseCase, deleteCartItemLocalUseCase, orderListLocalUseCase)

    @Provides
    fun provideRemoteUseCase(
        allCategoriesUseCase: AllCategoriesUseCase,
        subCategoriesUseCase: SubCategoriesUseCase,
        subCategoryDetailsUseCase: SubCategoryDetailsUseCase,
        menuListUseCase: MenuListUseCase
    ) = RemoteUseCase(allCategoriesUseCase, subCategoriesUseCase, subCategoryDetailsUseCase, menuListUseCase)

    @Provides
    fun provideReadUserInfoLocalUseCase(
        repository: ReadUserInfoLocalRepository
    ): ReadUserInfoLocalUseCase {
        return ReadUserInfoLocalUseCase(repository)
    }

    @Provides
    fun provideWriteUserInfoLocalUseCase(
        repository: WriteUserInfoLocalRepository
    ): WriteUserInfoLocalUseCase {
        return WriteUserInfoLocalUseCase(repository)
    }

    @Provides
    fun provideClearUserInfoLocalUseCase(
        repository: ClearUserInfoLocalRepository
    ): ClearUserInfoLocalUseCase {
        return ClearUserInfoLocalUseCase(repository)
    }

    @Provides
    fun provideAuthUseCase(
        readUserInfoLocalUseCase: ReadUserInfoLocalUseCase,
        writeUserInfoLocalUseCase: WriteUserInfoLocalUseCase,
        clearUserInfoLocalUseCase: ClearUserInfoLocalUseCase
    ): AuthUseCase {
        return AuthUseCase(readUserInfoLocalUseCase, writeUserInfoLocalUseCase, clearUserInfoLocalUseCase)
    }
}