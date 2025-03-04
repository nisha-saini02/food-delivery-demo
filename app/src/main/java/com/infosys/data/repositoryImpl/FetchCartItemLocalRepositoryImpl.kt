package com.infosys.data.repositoryImpl

import com.infosys.data.datasource.FetchCartItemLocalDataSource
import com.infosys.data.model.cart.Cart
import com.infosys.domain.repository.FetchCartItemLocalRepository
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class FetchCartItemLocalRepositoryImpl @Inject constructor(var localDataSource: FetchCartItemLocalDataSource): FetchCartItemLocalRepository {
    override suspend fun fetchItem(itemId: String): Flow<Cart?> {
        return channelFlow {
            val result = localDataSource.fetchItem(itemId)
            trySend(result)
                .onFailure {
                    send(Cart())
                }
        }
    }
}