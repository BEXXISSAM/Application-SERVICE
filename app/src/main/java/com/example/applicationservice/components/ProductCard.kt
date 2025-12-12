package com.example.applicationservice.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.applicationservice.models.Product

@Composable
fun ProductCard(product: Product, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {

           AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(product.imageResId)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(id = product.nameResId),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
           )

                Surface(
                    color = Color.Black.copy(alpha = 0.7f),
                    shape = RoundedCornerShape(bottomStart = 8.dp),
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Text(
                        text = product.price,
                        color = Color.White,
                        modifier = Modifier.padding(8.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Text(
                text = stringResource(id = product.nameResId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}