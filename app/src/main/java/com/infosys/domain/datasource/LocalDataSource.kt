package com.infosys.domain.datasource

import com.infosys.data.model.cart.Cart
import com.infosys.domain.local.CartDao

class LocalDataSource (val cartDao: CartDao) {
    suspend fun fetchAllItems(): List<Cart>
    = cartDao.getAll()

    suspend fun fetchItem(itemId: String): Cart?
    = cartDao.getItem(itemId)

    suspend fun insertItem(cart: Cart): Long
    = cartDao.insertItem(cart)

    suspend fun updateItem(cart: Cart): Int
    = cartDao.updateItem(cart)

    suspend fun delete(cart: Cart): Int
    = cartDao.delete(cart)
}