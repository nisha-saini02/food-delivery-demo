package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.OrderDao
import com.infosys.data.model.order.Order
import com.infosys.domain.repository.InsertOrderItemLocalRepository
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class InsertOrderItemLocalRepositoryImpl @Inject constructor(var orderDao: OrderDao): InsertOrderItemLocalRepository {
    override suspend fun insertItem(order: Order): Flow<Long> {
        return channelFlow {
            val result = orderDao.insertItem(order)
            trySend(result)
                .onFailure {
                    send(-1)
                }
        }
    }
}