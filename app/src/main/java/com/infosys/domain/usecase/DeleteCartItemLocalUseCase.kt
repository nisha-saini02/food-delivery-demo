package com.infosys.domain.usecase

import com.infosys.data.model.cart.Cart
import com.infosys.domain.repository.DeleteCartItemLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteCartItemLocalUseCase @Inject constructor(var localRepository: DeleteCartItemLocalRepository) {
    suspend fun delete(cart: Cart): Flow<Int>
            = localRepository.delete(cart)
}