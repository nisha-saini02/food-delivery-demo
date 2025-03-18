package com.infosys.domain.usecase

import com.infosys.data.model.cart.Cart
import com.infosys.domain.repository.MenuCartLocalRepository

data class UpdateCartItemLocalUseCase (var localRepository: MenuCartLocalRepository) {
    suspend fun updateItem(cart: Cart)
            = localRepository.updateItem(cart)
}