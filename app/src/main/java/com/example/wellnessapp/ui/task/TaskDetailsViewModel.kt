
package com.example.wellnessapp.ui.task

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wellnessapp.data.TasksRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * ViewModel to retrieve, update and delete an item from the data source.
 */
class TaskDetailsViewModel(savedStateHandle: SavedStateHandle, private val itemsRepository: TasksRepository) : ViewModel() {

    private val itemId: Int = checkNotNull(savedStateHandle[TaskDetailsDestination.itemIdArg])

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val uiState: StateFlow<ItemUiState> =
        itemsRepository.getItemStream(itemId)
            .filterNotNull()
            .map {
                //it.toItemUiState()
                // it.toItemUiState(actionEnabled = true)
                //it.toItemUiState(actionEnabled =  it.quantity > 0
                it.toItemUiState(actionEnabled =  it.name != null
                )
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemUiState()
            )

    fun reduceQuantityByOne() {
        viewModelScope.launch {
            val currentItem = uiState.value.toItem()
            /*if (currentItem.quantity > 0) {
                itemsRepository.updateItem(currentItem.copy(quantity = currentItem.quantity - 1))
            }*/
        }
    }

    suspend fun deleteItem() {
        itemsRepository.deleteItem(uiState.value.toItem())
    }
}
