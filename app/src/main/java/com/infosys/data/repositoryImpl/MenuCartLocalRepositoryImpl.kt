package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.CartDao
import com.infosys.data.model.cart.Cart
import com.infosys.domain.repository.MenuCartLocalRepository

class MenuCartLocalRepositoryImpl (var cartDao: CartDao): MenuCartLocalRepository {
    override suspend fun insertItem(cart: Cart): Long {
        return try {
            cartDao.insertItem(cart)
        } catch (e: Exception) {
            -1
        }
    }

    override suspend fun updateItem(cart: Cart): Int {
        return try {
            cartDao.updateItem(cart)
        } catch (e: Exception) {
            -1
        }
    }

    override suspend fun delete(cart: Cart): Int {
        return try {
            cartDao.delete(cart)
        } catch (e: Exception) {
            -1
        }
    }
}