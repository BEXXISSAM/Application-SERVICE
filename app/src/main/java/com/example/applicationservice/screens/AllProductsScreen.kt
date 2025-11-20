package com.example.applicationservice.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.applicationservice.data.ProductDataSource

@Composable
fun AllProductsScreen(modifier: Modifier = Modifier) {
    val products = ProductDataSource().loadProducts()

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        items(products) { product ->
            ProductCard(product = product)
        }
    }
}