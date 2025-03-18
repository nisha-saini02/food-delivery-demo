package com.infosys.domain.repository

import com.infosys.data.model.cart.Cart

interface MenuCartLocalRepository {
    suspend fun insertItem(cart: Cart): Long
    suspend fun updateItem(cart: Cart): Int
    suspend fun delete(cart: Cart): Int
}