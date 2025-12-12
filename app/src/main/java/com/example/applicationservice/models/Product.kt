package com.example.applicationservice.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ProductId")
    val id:Int,

    @ColumnInfo(name="ProductName")
    val name:String,

    @ColumnInfo(name="ProductPrice")
    val price:Float,

    @ColumnInfo(name = "Description")
    val description:String
)