package com.infosys.domain.repository

import com.infosys.data.model.cart.Cart
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun fetchAllItems(): Flow<List<Cart>>
}