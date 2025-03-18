package com.infosys.domain.usecase

import com.infosys.domain.repository.CartsDetailLocalRepository

class CountCartItemsLocalUseCase (var repo: CartsDetailLocalRepository) {
    suspend fun getCartListCount() = repo.getCartListCount()
}