package com.infosys.domain.usecase

import com.infosys.data.model.cart.Cart
import com.infosys.domain.repository.MenuCartLocalRepository

data class DeleteCartLocalUseCase(var localRepository: MenuCartLocalRepository) {
    suspend fun delete(cart: Cart)
            = localRepository.delete(cart)
}