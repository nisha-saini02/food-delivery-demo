package com.infosys.domain.usecase

import com.infosys.data.model.order.Order
import com.infosys.domain.repository.InsertOrderItemLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InsertOrderItemLocalUseCase @Inject constructor(var repo: InsertOrderItemLocalRepository) {
    suspend fun insertItem(order: Order): Flow<Long>
            = repo.insertItem(order)
}