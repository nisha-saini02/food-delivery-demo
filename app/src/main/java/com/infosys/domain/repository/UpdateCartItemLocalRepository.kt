package com.infosys.domain.repository

import com.infosys.data.model.cart.Cart
import com.infosys.data.remote.Resource
import kotlinx.coroutines.flow.Flow

interface UpdateCartItemLocalRepository {
    suspend fun updateItem(cart: Cart): Flow<Resource<Int>>
}