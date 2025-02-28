package com.infosys.domain.usecase

import com.infosys.data.model.cart.Cart
import com.infosys.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class LocalUseCase(val localRepository: LocalRepository) {
    suspend fun fetchAllItems(): Flow<List<Cart>>
            = localRepository.fetchAllItems()
}