package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.CartDao
import com.infosys.data.model.cart.Cart
import com.infosys.data.remote.Resource
import com.infosys.domain.repository.AllCartItemsLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class AllCartItemsLocalRepositoryImpl @Inject constructor(var cartDao: CartDao): AllCartItemsLocalRepository {
    override suspend fun fetchAllItems(): Flow<Resource<List<Cart>>> {
        return channelFlow {
            try {
                val result = cartDao.getAll()
                trySend(Resource.Success(result))
            } catch (e: Exception) {
                send(Resource.Error(e.message.toString()))
            }
        }
    }
}