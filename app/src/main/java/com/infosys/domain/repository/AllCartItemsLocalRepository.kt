package com.infosys.domain.repository

import com.infosys.data.model.cart.Cart
import kotlinx.coroutines.flow.Flow

interface AllCartItemsLocalRepository {
    suspend fun fetchAllItems(): Flow<List<Cart>>
}