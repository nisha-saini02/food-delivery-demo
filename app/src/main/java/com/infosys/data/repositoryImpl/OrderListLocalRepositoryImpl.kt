package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.OrderDao
import com.infosys.data.model.order.Order
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.OrderListLocalRepository
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class OrderListLocalRepositoryImpl @Inject constructor(var orderDao: OrderDao): OrderListLocalRepository {
    override suspend fun orderList(): Flow<Resource<List<Order>>> {
        return channelFlow {
            val result = orderDao.getAll()
            trySend(Resource.Success(result))
                .onFailure {
                    send(Resource.Error(it?.message.toString()))
                }
        }
    }
}