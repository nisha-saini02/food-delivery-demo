package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.CartDao
import com.infosys.data.model.cart.Cart
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.FetchCartItemLocalRepository
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class FetchCartItemLocalRepositoryImpl @Inject constructor(var cartDao: CartDao): FetchCartItemLocalRepository {
    override suspend fun fetchItem(itemId: String): Flow<Resource<Cart?>> {
        return channelFlow {
            val result = cartDao.getItem(itemId)
            trySend(Resource.Success(result))
                .onFailure {
                    send(Resource.Error(it?.message.toString()))
                }
        }
    }
}