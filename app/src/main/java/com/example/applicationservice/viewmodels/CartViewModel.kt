package com.example.applicationservice.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applicationservice.models.CartItem
import com.example.applicationservice.models.Product
import com.example.applicationservice.repositories.CartRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CartViewModel(private val repository: CartRepository) : ViewModel() {

    val cartItems: StateFlow<List<CartItem>> = repository.allCartItems
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addToCart(product: Product) {
        viewModelScope.launch {
            val item = CartItem(
                id = 0,
                productId = product.id,
                name = product.name,
                price = product.price,
                imageUrl = product.imageUrl,
                quantity = 1
            )
            repository.addToCart(item)
        }
    }

    fun remove(item: CartItem) {
        viewModelScope.launch {
            repository.removeFromCart(item)
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            repository.clearCart()
        }
    }
}