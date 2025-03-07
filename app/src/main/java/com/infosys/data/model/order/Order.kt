package com.infosys.data.model.order

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Order (
    @PrimaryKey (autoGenerate = true)
    val id: Long? = null,
    @ColumnInfo(name = "source_lat")
    var sourceLat: Double? = null,
    @ColumnInfo(name = "source_long")
    var sourceLong: Double? = null,
    @ColumnInfo(name = "destination_lat")
    var destinationLat: Double? = null,
    @ColumnInfo(name = "destination_long")
    var destinationLong: Double? = null,
    @ColumnInfo(name = "cart_count")
    var orderItems: Int? = null,
    @ColumnInfo(name = "cart_grand_total")
    var orderGrandTotal: Float? = null
)