package com.infosys.domain.usecase

import com.infosys.data.model.order.Order
import com.infosys.domain.repository.OrderListLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OrderListLocalUseCase @Inject constructor(var repo: OrderListLocalRepository) {
    suspend fun orderList(): Flow<List<Order>>
    = repo.orderList()
}