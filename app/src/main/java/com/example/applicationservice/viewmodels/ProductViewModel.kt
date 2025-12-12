package com.example.applicationservice.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.applicationservice.databases.ProductRoomDatabase
import com.example.applicationservice.models.Product
import com.example.applicationservice.repositories.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application): AndroidViewModel(application) {
    private val repository : ProductRepository
    val allProducts: LiveData<List<Product>>
    init {
        val productDao = ProductRoomDatabase.getDatabase(application).productDao ()
        repository = ProductRepository(productDao)
                allProducts = repository.allProducts
    }
    fun insert(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(product)
    }
}
