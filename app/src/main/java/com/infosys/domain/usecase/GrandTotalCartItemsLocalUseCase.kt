package com.infosys.domain.usecase

import com.infosys.domain.repository.CartsDetailLocalRepository

class GrandTotalCartItemsLocalUseCase (var repo: CartsDetailLocalRepository) {
    suspend fun getCartGrandSum() = repo.getCartListGrandTotalCount()
}