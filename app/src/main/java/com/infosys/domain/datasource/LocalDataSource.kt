package com.infosys.domain.datasource

import com.infosys.data.model.cart.Cart
import com.infosys.domain.local.CartDao

class LocalDataSource (val cartDao: CartDao) {
    suspend fun fetchAllItems(): List<Cart>
    = cartDao.getAll()
}