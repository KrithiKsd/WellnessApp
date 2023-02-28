package com.example.wellnessapp.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.wellnessapp.TaskApplication
import com.example.wellnessapp.ui.home.HomeViewModel
import com.example.wellnessapp.ui.task.TaskDetailsViewModel
import com.example.wellnessapp.ui.task.TaskEditViewModel
import com.example.wellnessapp.ui.task.TaskEntryViewModel


object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEditViewModel
        initializer {
            TaskEditViewModel(
                this.createSavedStateHandle(),
                inventoryApplication().container.itemsRepository
            )
        }
        // Initializer for ItemEntryViewModel
        initializer {
            TaskEntryViewModel(inventoryApplication().container.itemsRepository)
        }

        // Initializer for ItemDetailsViewModel
        initializer {
            TaskDetailsViewModel(
                this.createSavedStateHandle(),
                inventoryApplication().container.itemsRepository
            )
        }

        // Initializer for HomeViewModel
        initializer {
           // HomeViewModel()
            HomeViewModel(inventoryApplication().container.itemsRepository)
        }
    }
}
fun CreationExtras.inventoryApplication(): TaskApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TaskApplication)