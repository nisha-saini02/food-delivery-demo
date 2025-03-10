package com.infosys.domain.repository

import com.infosys.data.model.order.Order
import com.infosys.data.remote.Resource
import kotlinx.coroutines.flow.Flow

interface InsertOrderItemLocalRepository {
    suspend fun insertItem(order: Order): Flow<Resource<Long>>
}