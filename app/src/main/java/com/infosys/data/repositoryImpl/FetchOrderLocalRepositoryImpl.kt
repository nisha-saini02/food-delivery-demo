package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.OrderDao
import com.infosys.data.model.order.Order
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.FetchOrderLocalRepository
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class FetchOrderLocalRepositoryImpl @Inject constructor(var dao: OrderDao) : FetchOrderLocalRepository {
    override suspend fun getOrder(itemId: String): Flow<Resource<Order?>> {
        return channelFlow {
            trySend(Resource.Success(dao.getOrder(itemId)))
                .onFailure {
                    send(Resource.Error(it?.message.toString()))
                }
        }
    }
}