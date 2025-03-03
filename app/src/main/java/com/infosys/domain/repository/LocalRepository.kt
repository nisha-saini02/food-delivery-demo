package com.infosys.domain.repository

import com.infosys.data.model.cart.Cart
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun fetchAllItems(): Flow<List<Cart>>
    suspend fun fetchItem(itemId: String): Flow<Cart?>
    suspend fun insertItem(cart: Cart): Flow<Long>
    suspend fun updateItem(cart: Cart): Flow<Int>
    suspend fun delete(cart: Cart): Flow<Int>
}