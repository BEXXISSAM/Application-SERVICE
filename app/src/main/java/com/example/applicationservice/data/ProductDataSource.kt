package com.example.applicationservice.data

import com.example.applicationservice.R
import com.example.applicationservice.models.Product

class ProductDataSource {
    fun loadProducts(): List<Product> {
        return listOf(
            Product(R.string.product_1, "6000 DH", R.drawable.image_1),
            Product(R.string.product_2, "7500 DH", R.drawable.image_2),
            Product(R.string.product_3, "9200 DH", R.drawable.image_3),
            Product(R.string.product_4, "6200 DH", R.drawable.image_4),
            Product(R.string.product_5, "299 DH", R.drawable.image_5),
            Product(R.string.product_6, "599 DH", R.drawable.image_6),
        )
    }
}