package com.infosys.domain.repository

import com.infosys.data.model.cart.Cart
import kotlinx.coroutines.flow.Flow

interface FetchCartItemLocalRepository {
    suspend fun fetchItem(itemId: String): Flow<Cart?>
}