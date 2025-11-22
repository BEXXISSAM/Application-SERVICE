package com.example.applicationservice.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.applicationservice.R
import com.example.applicationservice.models.Product


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
        navController: NavController,
        product: Product
) {
    Scaffold(
            topBar = {
                    TopAppBar(
                            title = { Text(text = stringResource(id = R.string.top_bar_ap)) },
                            navigationIcon = {
                                    IconButton(onClick = { navController.popBackStack() }) {
                                    Icon(
                                            imageVector = Icons.Default.ArrowBack,
                                            contentDescription = stringResource(id = R.string.retour_icon)
                                    )
                            }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    titleContentColor = MaterialTheme.colorScheme.primary
                            )
                    )
            }
    ) { innerPadding ->
            Column(
                    modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
            ) {
        Box(
                modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
        ) {
            Image(
                    painter = painterResource(id = product.imageResId),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
            )
        }

        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
            ) {
                // Titre
                Text(
                        text = stringResource(id = product.nameResId),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                )
                Text(
                        text = product.price,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                    onClick = { navController.navigate("order_screen/${product.nameResId}") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = stringResource(id = R.string.order))
            }
        }
    }
    }
}