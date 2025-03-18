package com.infosys.domain.repository

import com.infosys.data.model.cart.Cart

interface CartsDetailLocalRepository {
    suspend fun fetchAllItems(): List<Cart>?
    suspend fun getCartListCount(): Int?
    suspend fun getCartListGrandTotalCount(): Float?
    suspend fun deleteAllCarts(): Int?
}