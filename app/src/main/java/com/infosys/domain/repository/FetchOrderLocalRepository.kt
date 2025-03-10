package com.infosys.domain.repository

import com.infosys.data.model.order.Order
import com.infosys.data.remote.Resource
import kotlinx.coroutines.flow.Flow

interface FetchOrderLocalRepository {
    suspend fun getOrder(itemId: String): Flow<Resource<Order?>>
}