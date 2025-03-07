package com.infosys.data.localDatabase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.infosys.data.model.cart.Cart

@Dao
interface CartDao {
    @Query("SELECT * FROM cart")
    suspend fun getAll(): List<Cart>

    @Query("SELECT * FROM cart where id = :itemId")
    suspend fun getItem(itemId: String): Cart?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(cart: Cart): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateItem(cart: Cart): Int

    @Delete
    suspend fun delete(cart: Cart): Int
}