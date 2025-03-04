package com.infosys.data.datasource

import com.infosys.data.local.CartDao
import com.infosys.data.model.cart.Cart
import javax.inject.Inject

class DeleteCartItemLocalDataSource @Inject constructor(var cartDao: CartDao) {
    suspend fun delete(cart: Cart): Int
            = cartDao.delete(cart)
}