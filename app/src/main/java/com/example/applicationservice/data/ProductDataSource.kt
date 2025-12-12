package com.example.applicationservice.data

import com.example.applicationservice.models.Product

class ProductDataSource {
    fun loadProducts(): List<Product> {
        return listOf(
            Product(,, "6000 DH"),
            Product(,, "7500 DH"),
            Product(,, "9200 DH"),
            Product(,, "6200 DH"),
            Product(,, "299 DH"),
            Product(,, "599 DH"),
        )
    }
}