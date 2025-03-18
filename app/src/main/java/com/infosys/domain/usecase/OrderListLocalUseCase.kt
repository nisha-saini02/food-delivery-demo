package com.infosys.domain.usecase

import com.infosys.domain.repository.OrdersLocalRepository

class OrderListLocalUseCase(var repo: OrdersLocalRepository) {
    suspend fun orderList() = repo.orderList()
}