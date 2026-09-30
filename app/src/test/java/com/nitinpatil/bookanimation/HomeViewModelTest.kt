package com.nitinpatil.bookanimation

import com.nitinpatil.bookanimation.ui.viewmodel.HomeViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class HomeViewModelTest {

    @Test
    fun testSelectCategoryUpdatesState() {
        val viewModel = HomeViewModel()
        assertEquals("All", viewModel.uiState.value.selectedCategory)

        viewModel.selectCategory("Sofa")
        assertEquals("Sofa", viewModel.uiState.value.selectedCategory)
    }

    @Test
    fun testSearchQueryUpdatesState() {
        val viewModel = HomeViewModel()
        assertEquals("", viewModel.uiState.value.searchQuery)

        viewModel.updateSearchQuery("Table")
        assertEquals("Table", viewModel.uiState.value.searchQuery)
    }

    @Test
    fun testShowCollectionDetailUpdatesState() {
        val viewModel = HomeViewModel()
        assertNull(viewModel.uiState.value.selectedCollectionItem)

        val testItem = viewModel.uiState.value.collections[0]
        viewModel.showCollectionDetail(testItem)
        assertEquals(testItem, viewModel.uiState.value.selectedCollectionItem)

        viewModel.showCollectionDetail(null)
        assertNull(viewModel.uiState.value.selectedCollectionItem)
    }
}
