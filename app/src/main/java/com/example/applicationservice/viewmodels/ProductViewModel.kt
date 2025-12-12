package com.example.applicationservice.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.applicationservice.databases.AppDatabase
import com.example.applicationservice.models.Product
import com.example.applicationservice.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ProductRepository

    val allProducts: Flow<List<Product>>

    init {
        val database = AppDatabase.getDatabase(application)
        val productDao = database.productDao()
        repository = ProductRepository(productDao)
        allProducts = repository.getAllProductsFromDb()

        fetchFromApi()
    }

    fun fetchFromApi() {
        viewModelScope.launch {
            repository.refreshProducts()
        }
    }
}