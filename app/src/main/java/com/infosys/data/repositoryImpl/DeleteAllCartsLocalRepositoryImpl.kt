package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.CartDao
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.DeleteAllCartsLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class DeleteAllCartsLocalRepositoryImpl @Inject constructor(var dao: CartDao): DeleteAllCartsLocalRepository {
    override suspend fun deleteAllCarts(): Flow<Resource<Int>> {
        return channelFlow {
            try {
                trySend(Resource.Success(dao.deleteAllCarts()))
            } catch (e: Exception) {
                send(Resource.Error(e.message.toString()))
            }
        }
    }
}