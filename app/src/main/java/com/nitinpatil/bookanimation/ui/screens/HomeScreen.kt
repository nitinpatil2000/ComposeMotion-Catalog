package com.nitinpatil.bookanimation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nitinpatil.bookanimation.ui.components.CategoryChipsRow
import com.nitinpatil.bookanimation.ui.components.FurnitureCollectionList
import com.nitinpatil.bookanimation.ui.components.HeaderSection
import com.nitinpatil.bookanimation.ui.components.PopularProductsSection
import com.nitinpatil.bookanimation.ui.components.SearchBarSection
import com.nitinpatil.bookanimation.ui.viewmodel.HomeViewModel


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var hasComposeBefore by rememberSaveable {
        mutableStateOf(false)
    }
    val skipAnimation = remember {
        hasComposeBefore
    }

    LaunchedEffect(Unit) {
        if (hasComposeBefore) {
            viewModel.completeAnimationImmediately()
        } else {
            viewModel.startSequentialAnimation()
        }

        hasComposeBefore = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .systemBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        HeaderSection(startAnimation = uiState.isHeaderVisible)
        SearchBarSection(
            startAnimation = uiState.isSearchVisible,
            query = uiState.searchQuery,
            onQueryChanged = { query ->
                viewModel.updateSearchQuery(query)
            })
        Spacer(modifier = Modifier.height(15.dp))
        CategoryChipsRow(
            categories = uiState.categories,
            selectedCategory = uiState.selectedCategory,
            isVisible = uiState.isChipsVisible,
            onCategorySelected = { category ->
                viewModel.selectCategory(category)
            }
        )
        FurnitureCollectionList(
            items = uiState.collections,
            startAnimation = uiState.isCollectionVisible,
            skipAnimation = skipAnimation
        )
        PopularProductsSection(
            products = uiState.popularProducts,
            startAnimation = uiState.isPopularVisible,
            skipAnimation = skipAnimation
        )
    }
}