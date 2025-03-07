package com.infosys.domain.repository

import com.infosys.data.model.order.Order
import kotlinx.coroutines.flow.Flow

interface InsertOrderItemLocalRepository {
    suspend fun insertItem(order: Order): Flow<Long>
}