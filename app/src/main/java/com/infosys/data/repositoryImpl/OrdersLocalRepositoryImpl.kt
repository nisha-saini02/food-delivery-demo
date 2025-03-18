package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.OrderDao
import com.infosys.data.model.order.Order
import com.infosys.domain.repository.OrdersLocalRepository

class OrdersLocalRepositoryImpl(private var orderDao: OrderDao): OrdersLocalRepository {
    override suspend fun orderList(): List<Order>? {
        return try {
            orderDao.getAll()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getOrder(itemId: String): Order? {
        return try {
            orderDao.getOrder(itemId)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun insertItem(order: Order): Long {
        return try {
            orderDao.insertItem(order)
        } catch (e: Exception) {
            -1
        }
    }
}