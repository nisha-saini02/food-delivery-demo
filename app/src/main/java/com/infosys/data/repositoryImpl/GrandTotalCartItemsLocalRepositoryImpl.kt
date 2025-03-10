package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.CartDao
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.GrandTotalCartItemsLocalRepository
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class GrandTotalCartItemsLocalRepositoryImpl @Inject constructor(var dao: CartDao): GrandTotalCartItemsLocalRepository {
    override suspend fun getCartListGrandTotalCount(): Flow<Resource<Float?>> {
        return channelFlow {
            trySend(Resource.Success(dao.getCartGrandSum()))
                .onFailure {
                    send(Resource.Error(it?.message.toString()))
                }
        }
    }
}