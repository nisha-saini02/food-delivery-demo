package com.infosys.domain.repository

import com.infosys.data.model.order.Order
import kotlinx.coroutines.flow.Flow

interface OrderListLocalRepository {
    suspend fun orderList(): Flow<List<Order>>
}