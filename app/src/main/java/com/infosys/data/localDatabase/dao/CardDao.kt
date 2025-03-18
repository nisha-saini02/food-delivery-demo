package com.infosys.data.localDatabase.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.infosys.data.model.card.Card

@Dao
interface CardDao {
    @Query("SELECT * FROM card")
    suspend fun getAll(): List<Card>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(cart: Card): Long

    @Delete
    suspend fun deleteCard(card: Card): Int
}