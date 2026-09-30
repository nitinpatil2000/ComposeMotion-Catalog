package com.nitinpatil.bookanimation.data

import androidx.compose.ui.graphics.Color
import com.nitinpatil.bookanimation.R

data class CollectionItem(
    val title: String,
    val discount: String,
    val imageRes: Int
)

data class ProductItem(
    val title: String,
    val description: String,
    val price: String,
    val imageRes: Int,
    val bgColor: Color
)