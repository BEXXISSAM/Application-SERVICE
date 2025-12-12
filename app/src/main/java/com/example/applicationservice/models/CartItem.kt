package com.example.applicationservice.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "cart_table")
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "product_id")
    val productId: Int = 0,

    @ColumnInfo(name = "product_name")
    val name: String,

    @ColumnInfo(name = "product_price")
    val price: Double,

    @ColumnInfo(name = "image_url")
    val imageUrl: String,

    @ColumnInfo(name = "quantity")
    var quantity: Int = 1
)