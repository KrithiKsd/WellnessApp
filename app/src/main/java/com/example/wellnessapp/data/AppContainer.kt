package com.example.wellnessapp.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val itemsRepository: TasksRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ItemsRepository]
     */
    override val itemsRepository: TasksRepository by lazy {

        OfflineTasksRepository(TaskDatabase.getDatabase(context).itemDao())
    }
}