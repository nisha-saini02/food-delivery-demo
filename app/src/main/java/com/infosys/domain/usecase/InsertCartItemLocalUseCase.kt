package com.infosys.domain.usecase

import com.infosys.data.model.cart.Cart
import com.infosys.domain.repository.MenuCartLocalRepository

data class InsertCartItemLocalUseCase (var localRepository: MenuCartLocalRepository) {
    suspend fun insertItem(cart: Cart)
            = localRepository.insertItem(cart)
}