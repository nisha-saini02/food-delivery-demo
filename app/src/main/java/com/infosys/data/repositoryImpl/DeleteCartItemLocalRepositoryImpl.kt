package com.infosys.data.repositoryImpl

import com.infosys.data.datasource.DeleteCartItemLocalDataSource
import com.infosys.data.model.cart.Cart
import com.infosys.domain.repository.DeleteCartItemLocalRepository
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class DeleteCartItemLocalRepositoryImpl @Inject constructor(var localDataSource: DeleteCartItemLocalDataSource): DeleteCartItemLocalRepository {
    override suspend fun delete(cart: Cart): Flow<Int> {
        return channelFlow {
            val result = localDataSource.delete(cart)
            trySend(result)
                .onFailure {
                    send(-1)
                }
        }
    }
}