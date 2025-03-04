package com.infosys.domain.usecase

import com.infosys.data.model.cart.Cart
import com.infosys.domain.repository.InsertCartItemLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InsertCartItemLocalUseCase @Inject constructor(var localRepository: InsertCartItemLocalRepository) {
    suspend fun insertItem(cart: Cart): Flow<Long>
            = localRepository.insertItem(cart)
}