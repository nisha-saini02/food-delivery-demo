package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.CartDao
import com.infosys.data.model.cart.Cart
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.InsertCartItemLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class InsertCartItemLocalRepositoryImpl @Inject constructor(var cartDao: CartDao): InsertCartItemLocalRepository {
    override suspend fun insertItem(cart: Cart): Flow<Resource<Long>> {
        return channelFlow {
            try {
                trySend(Resource.Success(cartDao.insertItem(cart)))
            } catch (e: Exception) {
                send(Resource.Error(e.message.toString()))
            }
        }
    }
}