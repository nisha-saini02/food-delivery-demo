package com.infosys.domain.usecase

import com.infosys.data.model.cart.Cart
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.AllCartItemsLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AllCartItemsLocalUseCase @Inject constructor(var localRepository: AllCartItemsLocalRepository) {
    suspend fun fetchAllItems(): Flow<Resource<List<Cart>>>
            = localRepository.fetchAllItems()
}