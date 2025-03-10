package com.infosys.domain.usecase

import com.infosys.domain.repository.OrderListLocalRepository
import javax.inject.Inject

class OrderListLocalUseCase @Inject constructor(var repo: OrderListLocalRepository) {
    suspend fun orderList() = repo.orderList()
}