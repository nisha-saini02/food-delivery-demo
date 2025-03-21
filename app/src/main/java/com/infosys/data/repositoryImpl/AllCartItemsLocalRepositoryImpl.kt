package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.CartDao
import com.infosys.data.model.cart.Cart
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.AllCartItemsLocalRepository
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class AllCartItemsLocalRepositoryImpl @Inject constructor(var cartDao: CartDao): AllCartItemsLocalRepository {
    override suspend fun fetchAllItems(): Flow<Resource<List<Cart>>> {
        return channelFlow {
            val result = cartDao.getAll()
            trySend(Resource.Success(result))
                .onFailure {
                    send(Resource.Error(it?.message.toString()))
                }
        }
    }
}