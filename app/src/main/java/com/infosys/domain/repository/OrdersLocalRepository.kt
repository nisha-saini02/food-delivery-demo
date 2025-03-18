package com.infosys.domain.repository

import com.infosys.data.model.order.Order

interface OrdersLocalRepository {
    suspend fun orderList(): List<Order>?
    suspend fun getOrder(itemId: String): Order?
    suspend fun insertItem(order: Order): Long
}