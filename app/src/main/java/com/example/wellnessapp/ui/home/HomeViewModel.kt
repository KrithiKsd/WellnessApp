
package com.example.wellnessapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wellnessapp.data.Task
import com.example.wellnessapp.data.TasksRepository
import kotlinx.coroutines.flow.*

/**
 * View Model to retrieve all items in the Room database.
 */
class HomeViewModel(itemsRepository: TasksRepository) : ViewModel() {
   /* val homeUiState: StateFlow<HomeUiState> =
    itemsRepository.getAllItemsStream().map { HomeUiState(it) }*/
   val homeUiState: StateFlow<HomeUiState> =
       itemsRepository.getAllItemsStream().map { HomeUiState(it) }
           .stateIn(
               scope = viewModelScope,
               started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
               initialValue = HomeUiState()
           )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    
}

/**
 * Ui State for HomeScreen
 */
data class HomeUiState(val itemList: List<Task> = listOf())


/*suspend fun deleteItem() {
    itemsRepository.deleteItem(uiState.value.toItem())
}*/
