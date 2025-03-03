package com.infosys.domain.usecase

import com.infosys.data.model.cart.Cart
import com.infosys.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class LocalUseCase(val localRepository: LocalRepository) {
    suspend fun fetchAllItems(): Flow<List<Cart>>
            = localRepository.fetchAllItems()

    suspend fun fetchItem(itemId: String): Flow<Cart?>
            = localRepository.fetchItem(itemId)

    suspend fun insertItem(cart: Cart): Flow<Long>
            = localRepository.insertItem(cart)

    suspend fun updateItem(cart: Cart): Flow<Int>
            = localRepository.updateItem(cart)

    suspend fun delete(cart: Cart): Flow<Int>
            = localRepository.delete(cart)
}