package com.example.applicationservice.models

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("products")
    val products: List<Product>,

    val total: Int,
    val skip: Int,
    val limit: Int
)