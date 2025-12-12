package com.example.applicationservice.repositories

import androidx.lifecycle.LiveData
import com.example.applicationservice.dao.ProductDao
import com.example.applicationservice.models.Product

class ProductRepository(private val productDao: ProductDao) {

    val allProducts: LiveData<List<Product>> = productDao.getProducts()

    suspend fun insert(product: Product) {
        productDao.insertProduct(product)
    }

    suspend fun deleteALL() {
        productDao.deleteProducts()
    }
}