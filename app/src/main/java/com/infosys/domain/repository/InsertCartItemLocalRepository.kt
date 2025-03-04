package com.infosys.domain.repository

import com.infosys.data.model.cart.Cart
import kotlinx.coroutines.flow.Flow

interface InsertCartItemLocalRepository {
    suspend fun insertItem(cart: Cart): Flow<Long>
}