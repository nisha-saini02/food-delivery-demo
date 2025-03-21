package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.CartDao
import com.infosys.data.model.cart.Cart
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.InsertCartItemLocalRepository
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class InsertCartItemLocalRepositoryImpl @Inject constructor(var cartDao: CartDao): InsertCartItemLocalRepository {
    override suspend fun insertItem(cart: Cart): Flow<Resource<Long>> {
        return channelFlow {
            val result = cartDao.insertItem(cart)
            trySend(Resource.Success(result))
                .onFailure {
                    send(Resource.Error(it?.message.toString()))
                }
        }
    }
}