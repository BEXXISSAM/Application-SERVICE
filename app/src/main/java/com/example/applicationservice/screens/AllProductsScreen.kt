package com.example.applicationservice.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.applicationservice.components.ServiceTopBar
import com.example.applicationservice.models.Product
import com.example.applicationservice.viewmodels.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllProductsScreen(
    viewModel: ProductViewModel,
    onProductClick: (Product) -> Unit,
    onLogout: () -> Unit
) {
    val productList by viewModel.allProducts.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            ServiceTopBar(
                canNavigateBack = false,
                onLogoutClick = onLogout
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (productList.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(productList) { product ->
                        ProductItem(
                            product = product,
                            onClick = { onProductClick(product) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, onClick: () -> Unit) {
    // ... Garde ton design de ProductItem ici (Carte), il est bon ...
    // Assure-toi juste d'utiliser AsyncImage pour l'image
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .clickable { onClick() }
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = null,
                modifier = Modifier.size(100.dp).clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = product.name, fontWeight = FontWeight.Bold)
                Text(text = "${product.price} $", color = Color.Green)
            }
        }
    }
}