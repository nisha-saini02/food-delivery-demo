package com.infosys.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.infosys.data.model.cart.Cart

@Database(entities = [Cart::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}