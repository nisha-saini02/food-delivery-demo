package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.CartDao
import com.infosys.domain.repository.GrandTotalCartItemsLocalRepository
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class GrandTotalCartItemsLocalRepositoryImpl @Inject constructor(var dao: CartDao): GrandTotalCartItemsLocalRepository {
    override suspend fun getCartListGrandTotalCount(): Flow<Float?> {
        return channelFlow {
            trySend(dao.getCartGrandSum())
                .onFailure {
                    send(-1f)
                }
        }
    }
}