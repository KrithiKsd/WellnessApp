package com.example.wellnessapp.data

/*
* Filename: TasksRepository.kt
* Author: Krithika Kasaragod
* */

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Item] from a given data source.
 */
interface TasksRepository{
    /**
     * Retrieve all the items from the the given data source.
     */
    fun getAllItemsStream(): Flow<List<Task>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getItemStream(id: Int): Flow<Task?>

    /**
     * Insert item in the data source
     */
    suspend fun insertItem(item: Task)

    /**
     * Delete item from the data source
     */
    suspend fun deleteItem(item: Task)

    /**
     * Update item in the data source
     */
    suspend fun updateItem(item: Task)
}
