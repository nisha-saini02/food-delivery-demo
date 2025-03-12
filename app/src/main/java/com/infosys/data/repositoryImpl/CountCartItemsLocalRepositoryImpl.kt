package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.CartDao
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.CountCartItemsLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class CountCartItemsLocalRepositoryImpl @Inject constructor(var dao: CartDao): CountCartItemsLocalRepository {
    override suspend fun getCartListCount(): Flow<Resource<Int?>> {
        return channelFlow {
            try {
                trySend(Resource.Success(dao.getCartListCount()))
            } catch (e: Exception) {
                send(Resource.Error(e.message.toString()))
            }
        }
    }
}