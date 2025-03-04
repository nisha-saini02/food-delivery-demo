package com.infosys.data.repositoryImpl

import com.infosys.data.datasource.UpdateCartItemLocalDataSource
import com.infosys.data.model.cart.Cart
import com.infosys.domain.repository.UpdateCartItemLocalRepository
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class UpdateCartItemLocalRepositoryImpl @Inject constructor(var localDataSource: UpdateCartItemLocalDataSource): UpdateCartItemLocalRepository {
    override suspend fun updateItem(cart: Cart): Flow<Int> {
        return channelFlow {
            val result = localDataSource.updateItem(cart)
            trySend(result)
                .onFailure {
                    send(-1)
                }
        }
    }
}