package com.infosys.data.datasource

import com.infosys.data.local.CartDao
import com.infosys.data.model.cart.Cart
import javax.inject.Inject

class InsertCartItemLocalDataSource @Inject constructor(var cartDao: CartDao) {
    suspend fun insertItem(cart: Cart): Long
            = cartDao.insertItem(cart)
}