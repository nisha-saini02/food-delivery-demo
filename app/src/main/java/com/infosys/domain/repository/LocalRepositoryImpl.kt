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

    override suspend fun fetchItem(itemId: String): Flow<Cart?> {
        return channelFlow {
            val result = localDataSource.fetchItem(itemId)
            trySend(result)
                .onFailure {
                    send(Cart())
                }
        }
    }

    override suspend fun insertItem(cart: Cart): Flow<Long> {
        return channelFlow {
            val result = localDataSource.insertItem(cart)
            trySend(result)
                .onFailure {
                    send(-1)
                }
        }
    }

    override suspend fun updateItem(cart: Cart): Flow<Int> {
        return channelFlow {
            val result = localDataSource.updateItem(cart)
            trySend(result)
                .onFailure {
                    send(-1)
                }
        }
    }

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