package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.CartDao
import com.infosys.data.model.cart.Cart
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.FetchCartItemLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class FetchCartItemLocalRepositoryImpl @Inject constructor(var cartDao: CartDao): FetchCartItemLocalRepository {
    override suspend fun fetchItem(itemId: String): Flow<Resource<Cart?>> {
        return channelFlow {
            try {
                trySend(Resource.Success(cartDao.getItem(itemId)))
            } catch (e: Exception) {
                send(Resource.Error(e.message.toString()))
            }
        }
    }
}