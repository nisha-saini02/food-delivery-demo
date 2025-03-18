package com.infosys.data.repositoryImpl

import com.infosys.data.localDatabase.dao.CartDao
import com.infosys.data.model.cart.Cart
import com.infosys.domain.repository.CartsDetailLocalRepository

class CartsDetailLocalRepositoryImpl (var cartDao: CartDao): CartsDetailLocalRepository {
    override suspend fun fetchAllItems(): List<Cart>? {
        return try {
            val result = cartDao.getAll()
            result
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getCartListCount(): Int? {
        return try {
            cartDao.getCartListCount()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getCartListGrandTotalCount(): Float? {
        return try {
            cartDao.getCartGrandSum()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun deleteAllCarts(): Int? {
        return try {
            cartDao.deleteAllCarts()
        } catch (e: Exception) {
            null
        }
    }
}