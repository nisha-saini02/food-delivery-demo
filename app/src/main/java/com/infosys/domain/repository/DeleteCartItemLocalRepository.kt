package com.infosys.domain.repository

import com.infosys.data.model.cart.Cart
import com.infosys.data.remote.Resource
import kotlinx.coroutines.flow.Flow

interface DeleteCartItemLocalRepository {
    suspend fun delete(cart: Cart): Flow<Resource<Int>>
}