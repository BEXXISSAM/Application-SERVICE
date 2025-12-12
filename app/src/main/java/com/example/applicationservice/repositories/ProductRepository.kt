package com.example.applicationservice.repositories

import com.example.applicationservice.api.RetrofitInstance
import com.example.applicationservice.dao.ProductDao
class ProductRepository(private val productDao: ProductDao) {

    suspend fun refreshProducts() {
        try {
            val response = RetrofitInstance.api.getProducts()

            val productList = response.products

            if (productList.isNotEmpty()) {
                productDao.insertAll(productList)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getAllProductsFromDb() = productDao.getProducts()

}