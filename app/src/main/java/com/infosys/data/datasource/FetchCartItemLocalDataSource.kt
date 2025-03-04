package com.infosys.data.datasource

import com.infosys.data.local.CartDao
import com.infosys.data.model.cart.Cart
import javax.inject.Inject

class FetchCartItemLocalDataSource @Inject constructor(var cartDao: CartDao) {
    suspend fun fetchItem(itemId: String): Cart?
            = cartDao.getItem(itemId)
}