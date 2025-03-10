package com.infosys.domain.usecase

import com.infosys.data.model.order.Order
import com.infosys.domain.repository.InsertOrderItemLocalRepository
import javax.inject.Inject

class InsertOrderItemLocalUseCase @Inject constructor(var repo: InsertOrderItemLocalRepository) {
    suspend fun insertItem(order: Order) = repo.insertItem(order)
}