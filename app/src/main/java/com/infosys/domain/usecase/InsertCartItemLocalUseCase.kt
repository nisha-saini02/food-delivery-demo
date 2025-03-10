package com.infosys.domain.usecase

import com.infosys.data.model.cart.Cart
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.InsertCartItemLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InsertCartItemLocalUseCase @Inject constructor(var localRepository: InsertCartItemLocalRepository) {
    suspend fun insertItem(cart: Cart): Flow<Resource<Long>>
            = localRepository.insertItem(cart)
}