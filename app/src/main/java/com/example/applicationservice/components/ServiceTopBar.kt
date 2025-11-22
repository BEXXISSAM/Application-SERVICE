package com.example.applicationservice.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.applicationservice.R
import com.example.applicationservice.ui.theme.ServiceRed
import com.example.applicationservice.ui.theme.ServiceWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceTopBar(
    canNavigateBack: Boolean,
    onBackClick: () -> Unit = {},
    onLogoutClick: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ServiceRed,
            titleContentColor = ServiceWhite,
            actionIconContentColor = ServiceWhite,
            navigationIconContentColor = ServiceWhite
        ),
        title = {
            Image(
                painter = painterResource(id = R.drawable.awl),
                contentDescription = R.string.logo_content_description.toString(),
                modifier = Modifier
                    .height(40.dp)
            )
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = Text(text =stringResource( R.string.logo_content_description)).toString(),
                        tint = ServiceWhite
                    )
                }
            }
        },
        actions = {
            TextButton(onClick = onLogoutClick) {
                Text(
                    text = "Logout",
                    color = ServiceWhite,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    )
}