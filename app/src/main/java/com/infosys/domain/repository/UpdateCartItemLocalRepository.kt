package com.infosys.domain.repository

import com.infosys.data.model.cart.Cart
import kotlinx.coroutines.flow.Flow

interface UpdateCartItemLocalRepository {
    suspend fun updateItem(cart: Cart): Flow<Int>
}