package com.example.wellnessapp.ui
/*
* Filename: AppViewModelProvider.kt
* Author: Krithika Kasaragod
* */
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.wellnessapp.TaskApplication
import com.example.wellnessapp.ui.home.HomeViewModel
import com.example.wellnessapp.ui.task.TaskEntryViewModel


object AppViewModelProvider {
    val Factory = viewModelFactory {

        // Initializer for ItemEntryViewModel
        initializer {
            TaskEntryViewModel(inventoryApplication().container.itemsRepository)
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