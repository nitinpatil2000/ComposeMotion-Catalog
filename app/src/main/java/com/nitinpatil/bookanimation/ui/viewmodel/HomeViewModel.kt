package com.nitinpatil.bookanimation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nitinpatil.bookanimation.data.CollectionItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private var animationJob: Job?= null
    private var hasStarted = false

    fun selectCategory(category: String) {
        _uiState.update {
            it.copy(selectedCategory = category)
        }
    }

    fun updateSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }



    fun startSequentialAnimation() {
        if (hasStarted) return
        hasStarted = true

        animationJob = viewModelScope.launch {
            _uiState.update { it.copy(isHeaderVisible = true) }
            delay(2000.milliseconds)

            _uiState.update { it.copy(isSearchVisible = true) }
            delay(1500.milliseconds)

            _uiState.update { it.copy(isChipsVisible = true) }
            delay(3000.milliseconds)

            _uiState.update { it.copy(isCollectionVisible = true) }
            delay(4500.milliseconds)

            _uiState.update { it.copy(isPopularVisible = true) }
        }
    }


    fun completeAnimationImmediately() {
        animationJob?.cancel()
        hasStarted = true
        _uiState.update {
            it.copy(
                isHeaderVisible = true,
                isSearchVisible = true,
                isChipsVisible = true,
                isCollectionVisible = true,
                isPopularVisible = true
            )
        }
    }

    fun showCollectionDetail(item: CollectionItem?) {
        _uiState.update { it.copy(selectedCollectionItem = item) }
    }
}
