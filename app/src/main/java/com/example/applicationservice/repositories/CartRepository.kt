package com.example.applicationservice.repositories

import com.example.applicationservice.dao.CartDao
import com.example.applicationservice.models.CartItem
import kotlinx.coroutines.flow.Flow

class CartRepository(private val cartDao: CartDao) {

    val allCartItems: Flow<List<CartItem>> = cartDao.getCartItems()

    suspend fun addToCart(item: CartItem) {
        cartDao.addToCart(item)
    }

    suspend fun removeFromCart(item: CartItem) {
        cartDao.removeFromCart(item)
    }

    suspend fun clearCart() {
        cartDao.clearCart()
    }
}