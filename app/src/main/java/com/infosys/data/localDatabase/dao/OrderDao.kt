package com.infosys.data.localDatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.infosys.data.model.order.Order

@Dao
interface OrderDao {
    @Query("SELECT * FROM `order`")
    suspend fun getAll(): List<Order>

    @Query("SELECT * FROM `order` where id = :itemId")
    suspend fun getOrder(itemId: String): Order?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(order: Order): Long
}