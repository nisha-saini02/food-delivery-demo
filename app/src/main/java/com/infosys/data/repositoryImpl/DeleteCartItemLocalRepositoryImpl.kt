package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.CartDao
import com.infosys.data.model.cart.Cart
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.DeleteCartItemLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class DeleteCartItemLocalRepositoryImpl @Inject constructor(var cartDao: CartDao): DeleteCartItemLocalRepository {
    override suspend fun delete(cart: Cart): Flow<Resource<Int>> {
        return channelFlow {
            try {
                trySend(Resource.Success(cartDao.delete(cart)))
            } catch (e: Exception) {
                send(Resource.Error(e.message.toString()))
            }
        }
    }
}