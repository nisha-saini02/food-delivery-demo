package com.infosys.data.model.cart

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
data class Cart (
    @PrimaryKey
    @NonNull
    @NotNull
    val id: String = "",
    @ColumnInfo(name = "name")
    val name: String? = null,
    @ColumnInfo(name = "desc")
    val desc: String? = null,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String? = null,
    @ColumnInfo(name = "cart_count")
    var cartCount: Int? = null
)