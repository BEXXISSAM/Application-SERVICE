package com.example.applicationservice.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.applicationservice.R
import com.example.applicationservice.components.ServiceTopBar
import com.example.applicationservice.models.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    navController: NavController,
    product: Product,
    onLogout: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("1") }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            ServiceTopBar(
                canNavigateBack = true,
                onBackClick = { navController.popBackStack() },
                onLogoutClick = onLogout
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.order_summary_title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier.fillMaxWidth().height(100.dp)
            ) {
                Row(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                    AsyncImage(
                        model = product.imageUrl,
                        contentDescription = null,
                        modifier = Modifier.size(80.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {
                        Text(text = product.name, fontWeight = FontWeight.Bold)
                        Text(text = "${product.price} $", color = MaterialTheme.colorScheme.primary)
                    }
                }
            }

            HorizontalDivider()

            Text(
                text = stringResource(R.string.order_form_title),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(stringResource(R.string.label_fullname)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text(stringResource(R.string.label_address)) },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text(stringResource(R.string.label_phone)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (name.isNotBlank() && address.isNotBlank()) {
                        // Utilisation du Double directement
                        val successMsg = "Commande validée pour ${product.price} $"
                        Toast.makeText(context, successMsg, Toast.LENGTH_LONG).show()
                        navController.popBackStack("all_products", inclusive = false)
                    } else {
                        Toast.makeText(context, "Remplissez les champs", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text(stringResource(R.string.btn_confirm_order))
            }
        }
    }
}