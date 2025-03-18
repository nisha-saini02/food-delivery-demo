package com.infosys.data.model.card

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Card (
    @PrimaryKey (autoGenerate = true)
    val id: Long? = null,
    @ColumnInfo(name = "holder_name")
    val holderName: String? = null,
    @ColumnInfo(name = "account_number")
    val accountNumber: String? = null,
    @ColumnInfo(name = "expiry_month")
    val expiryMonth: String? = null,
    @ColumnInfo(name = "expiry_year")
    var expiryYear: String? = null,
    @ColumnInfo(name = "cvv")
    var cvv: String? = null,
    var isSelected: Boolean = false
)