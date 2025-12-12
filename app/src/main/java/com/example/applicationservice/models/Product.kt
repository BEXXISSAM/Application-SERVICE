package com.example.applicationservice.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Products")
data class Product(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "ProductId")
    val id:Int,

    @ColumnInfo(name="ProductName")
    val name:String,

    @ColumnInfo(name="ProductPrice")
    val price:Double,

    @ColumnInfo(name = "Description")
    val description:String,

    @ColumnInfo(name = "ImageUrl")
    val imageUrl: String
)