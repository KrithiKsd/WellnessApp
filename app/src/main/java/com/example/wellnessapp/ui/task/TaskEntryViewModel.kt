package com.example.wellnessapp.ui.task


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.wellnessapp.data.TasksRepository

/**
 * View Model to validate and insert items in the Room database.
 */
class TaskEntryViewModel(private val itemsRepository: TasksRepository) : ViewModel() {

    /**
     * Holds current item ui state
     */
    var itemUiState by mutableStateOf(ItemUiState())
        private set

    /**
     * Updates the [itemUiState] with the value provided in the argument. This method also triggers
     * a validation for input values.
     */
    fun updateUiState(newItemUiState: ItemUiState) {
        itemUiState = newItemUiState.copy( actionEnabled = newItemUiState.isValid())
    }

    suspend fun saveItem() {
        if (itemUiState.isValid()) {
            itemsRepository.insertItem(itemUiState.toItem())
        }
    }

   /* suspend fun deleteItemFrom() {
        itemsRepository.deleteItem(itemUiState.toItem())
    }*/

}
