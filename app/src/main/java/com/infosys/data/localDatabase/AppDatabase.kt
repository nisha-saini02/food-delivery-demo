package com.infosys.data.localDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.infosys.data.localDatabase.dao.CardDao
import com.infosys.data.localDatabase.dao.CartDao
import com.infosys.data.localDatabase.dao.OrderDao
import com.infosys.data.model.card.Card
import com.infosys.data.model.cart.Cart
import com.infosys.data.model.order.Order

@Database(entities = [Cart::class, Order::class, Card::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
    abstract fun orderDao(): OrderDao
    abstract fun cardDao(): CardDao
}