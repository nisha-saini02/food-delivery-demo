package com.infosys.data.localDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.infosys.data.localDatabase.dao.CartDao
import com.infosys.data.localDatabase.dao.OrderDao
import com.infosys.data.model.cart.Cart
import com.infosys.data.model.order.Order

@Database(entities = [Cart::class, Order::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
    abstract fun orderDao(): OrderDao
}