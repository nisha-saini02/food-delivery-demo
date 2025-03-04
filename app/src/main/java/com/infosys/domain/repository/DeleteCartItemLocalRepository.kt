package com.infosys.domain.repository

import com.infosys.data.model.cart.Cart
import kotlinx.coroutines.flow.Flow

interface DeleteCartItemLocalRepository {
    suspend fun delete(cart: Cart): Flow<Int>
}