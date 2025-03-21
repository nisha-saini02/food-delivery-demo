package com.infosys.domain.usecase

import com.infosys.data.model.cart.Cart
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.FetchCartItemLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchCartItemLocalUseCase @Inject constructor(var localRepository: FetchCartItemLocalRepository) {
    suspend fun fetchItem(itemId: String): Flow<Resource<Cart?>>
            = localRepository.fetchItem(itemId)
}