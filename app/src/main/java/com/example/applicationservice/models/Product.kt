package com.example.applicationservice.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Product(
    @StringRes val nameResId: Int,
    val price: String,
    @DrawableRes val imageResId: Int
)