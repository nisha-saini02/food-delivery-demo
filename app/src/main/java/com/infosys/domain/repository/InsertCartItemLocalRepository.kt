package com.infosys.domain.repository

import com.infosys.data.model.cart.Cart
import com.infosys.data.remote.Resource
import kotlinx.coroutines.flow.Flow

interface InsertCartItemLocalRepository {
    suspend fun insertItem(cart: Cart): Flow<Resource<Long>>
}