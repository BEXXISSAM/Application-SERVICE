package com.example.applicationservice.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Products")
data class Product(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "ProductId")
    val id: Int,

    @ColumnInfo(name="ProductName")
    @SerializedName("title")
    val name: String,

    @ColumnInfo(name="ProductPrice")
    val price: Double,

    @ColumnInfo(name = "Description")
    val description: String,

    @ColumnInfo(name = "ImageUrl")
    @SerializedName("thumbnail")
    val imageUrl: String
)