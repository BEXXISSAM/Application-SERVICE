package com.example.applicationservice.api

import com.example.applicationservice.models.ProductResponse
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getProducts(): ProductResponse
}