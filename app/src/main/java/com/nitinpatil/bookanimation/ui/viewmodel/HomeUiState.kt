package com.nitinpatil.bookanimation.ui.viewmodel

import androidx.compose.ui.graphics.Color
import com.nitinpatil.bookanimation.R
import com.nitinpatil.bookanimation.data.CollectionItem
import com.nitinpatil.bookanimation.data.ProductItem

data class HomeUiState(
    val categories: List<String> = listOf("All", "Sofa", "Chair", "Table", "Bed", "Lamp"),
    val selectedCategory: String = "All",
    val searchQuery: String = "",

    //Sequential animation steps
    val isHeaderVisible: Boolean = false,
    val isSearchVisible: Boolean = false,
    val isChipsVisible: Boolean = false,
    val isCollectionVisible: Boolean = false,
    val isPopularVisible: Boolean = false,

    val collections: List<CollectionItem> = listOf(
        CollectionItem("Special Choice", "Up to 30% OFF", R.drawable.light),
        CollectionItem("Super Collection", "Up to 40% OFF", R.drawable.red),
        CollectionItem("Lounge Chair", "Up to 25% OFF", R.drawable.yellow)
    ),
    val popularProducts: List<ProductItem> = listOf(
        ProductItem(
            "Fabric Sofa",
            "Modern fabric sofa for stylish everyday comfort.",
            "$249",
            R.drawable.bottom1,
            Color(0xFFB9CFE0)
        ),
        ProductItem(
            "Wooden Chair",
            "Minimalist wooden chair with ergonomic design.",
            "$129",
            R.drawable.bottom1,
            Color(0xFFE0C9B9)
        ),
        ProductItem(
            "Study Table",
            "Compact study table for small spaces.",
            "$199",
            R.drawable.bottom1,
            Color(0xFFC9E0B9)
        )
    ),

    val selectedCollectionItem: CollectionItem? = null,
)
