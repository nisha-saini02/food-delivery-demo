package com.infosys.data.localDatabase.dao

import androidx.room.Dao
import androidx.room.Query
import com.infosys.data.model.order.Order

@Dao
interface OrderDao {
    @Query("SELECT * FROM `order`")
    suspend fun getAll(): List<Order>
}