package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.CartDao
import com.infosys.domain.repository.DeleteAllCartsLocalRepository
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class DeleteAllCartsLocalRepositoryImpl @Inject constructor(var dao: CartDao): DeleteAllCartsLocalRepository {
    override suspend fun deleteAllCarts(): Flow<Int> {
        return channelFlow {
            trySend(dao.deleteAllCarts())
                .onFailure {
                    send(-1)
                }
        }
    }
}