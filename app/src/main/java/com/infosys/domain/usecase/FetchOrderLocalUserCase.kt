package com.infosys.domain.usecase

import com.infosys.domain.repository.OrdersLocalRepository

class FetchOrderLocalUserCase(var repo: OrdersLocalRepository) {
    suspend fun getOrder(id: String) = repo.getOrder(id)
}