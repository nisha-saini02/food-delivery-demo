package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.OrderDao
import com.infosys.data.model.order.Order
import com.infosys.domain.repository.FetchOrderLocalRepository
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class FetchOrderLocalRepositoryImpl @Inject constructor(var dao: OrderDao) : FetchOrderLocalRepository {
    override suspend fun getOrder(itemId: String): Flow<Order?> {
        return channelFlow {
            trySend(dao.getOrder(itemId))
                .onFailure {
                    send(null)
                }
        }
    }
}