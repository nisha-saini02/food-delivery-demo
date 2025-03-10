package com.infosys.domain.usecase

import com.infosys.data.model.cart.Cart
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.UpdateCartItemLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateCartItemLocalUseCase @Inject constructor(var localRepository: UpdateCartItemLocalRepository) {
    suspend fun updateItem(cart: Cart): Flow<Resource<Int>>
            = localRepository.updateItem(cart)
}