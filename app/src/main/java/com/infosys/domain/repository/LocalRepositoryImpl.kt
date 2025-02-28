package com.infosys.domain.repository

import com.infosys.data.model.cart.Cart
import com.infosys.domain.datasource.LocalDataSource
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

class LocalRepositoryImpl(private val localDataSource: LocalDataSource): LocalRepository {
    override suspend fun fetchAllItems(): Flow<List<Cart>> {
        return channelFlow {
            val result = localDataSource.fetchAllItems()
            trySend(result)
                .onFailure {
                    send(listOf())
                }
        }
    }
}