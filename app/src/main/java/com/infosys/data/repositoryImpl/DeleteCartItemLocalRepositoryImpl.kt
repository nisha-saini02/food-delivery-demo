package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.CartDao
import com.infosys.data.model.cart.Cart
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.DeleteCartItemLocalRepository
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class DeleteCartItemLocalRepositoryImpl @Inject constructor(var cartDao: CartDao): DeleteCartItemLocalRepository {
    override suspend fun delete(cart: Cart): Flow<Resource<Int>> {
        return channelFlow {
            val result = cartDao.delete(cart)
            trySend(Resource.Success(result))
                .onFailure {
                    send(Resource.Error(it?.message.toString()))
                }
        }
    }
}