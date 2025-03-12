package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.OrderDao
import com.infosys.data.model.order.Order
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.InsertOrderItemLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class InsertOrderItemLocalRepositoryImpl @Inject constructor(var orderDao: OrderDao): InsertOrderItemLocalRepository {
    override suspend fun insertItem(order: Order): Flow<Resource<Long>> {
        return channelFlow {
            try {
                trySend(Resource.Success(orderDao.insertItem(order)))
            } catch (e: Exception) {
                send(Resource.Error(e.message.toString()))
            }
        }
    }
}