package com.infosys.data.datasource

import com.infosys.data.local.CartDao
import com.infosys.data.model.cart.Cart
import javax.inject.Inject

class AllCartItemsLocalDataSource @Inject constructor(var cartDao: CartDao) {
    suspend fun fetchAllItems(): List<Cart>
            = cartDao.getAll()
}