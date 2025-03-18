package com.infosys.domain.usecase

import com.infosys.data.model.order.Order
import com.infosys.domain.repository.OrdersLocalRepository

class InsertOrderItemLocalUseCase (var repo: OrdersLocalRepository) {
    suspend fun insertItem(order: Order) = repo.insertItem(order)
}