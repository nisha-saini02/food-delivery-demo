package com.infosys.data.datasource

import com.infosys.data.local.CartDao
import com.infosys.data.model.cart.Cart
import javax.inject.Inject

class UpdateCartItemLocalDataSource @Inject constructor(var cartDao: CartDao) {
    suspend fun updateItem(cart: Cart): Int
            = cartDao.updateItem(cart)
}