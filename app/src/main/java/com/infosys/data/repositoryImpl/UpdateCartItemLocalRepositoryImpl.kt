package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.CartDao
import com.infosys.data.model.cart.Cart
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.UpdateCartItemLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class UpdateCartItemLocalRepositoryImpl @Inject constructor(var cartDao: CartDao): UpdateCartItemLocalRepository {
    override suspend fun updateItem(cart: Cart): Flow<Resource<Int>> {
        return channelFlow {
            try {
                trySend(Resource.Success(cartDao.updateItem(cart)))
            } catch (e: Exception) {
                send(Resource.Error(e.message.toString()))
            }
        }
    }
}