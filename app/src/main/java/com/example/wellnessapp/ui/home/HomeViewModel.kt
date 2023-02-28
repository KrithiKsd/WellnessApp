
package com.example.wellnessapp.ui.home
/*
* Filename: HomeViewModel.kt
* Author: Krithika Kasaragod
* */

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wellnessapp.data.Task
import com.example.wellnessapp.data.TasksRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Ui State for HomeScreen
 */
data class HomeUiState(val itemList: List<Task> = listOf())

class HomeViewModel(private val itemsRepository: TasksRepository) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("HomeViewModel", "Error deleting item", throwable)
    }

    init {
        viewModelScope.launch(coroutineExceptionHandler) {
            itemsRepository.getAllItemsStream()
                .map { itemList -> HomeUiState(itemList) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000),
                    initialValue = HomeUiState()
                )
                .collect { uiState ->
                    _homeUiState.value = uiState
                }
        }
    }

    fun deleteItem(item: Task) {
        viewModelScope.launch(coroutineExceptionHandler) {
            withContext(Dispatchers.IO) {
                itemsRepository.deleteItem(item)
            }
        }
    }
    fun updateItem(item: Task) {
        viewModelScope.launch(coroutineExceptionHandler) {
            withContext(Dispatchers.IO) {
                itemsRepository.updateItem(item)
            }
        }
    }

}
