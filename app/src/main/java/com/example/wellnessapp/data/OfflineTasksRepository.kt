package com.example.wellnessapp.data

/*
* Filename: OfflineTasksRepository.kt
* Author: Krithika Kasaragod
* */

import kotlinx.coroutines.flow.Flow

class OfflineTasksRepository(private val itemDao: ItemDao) : TasksRepository {
    override fun getAllItemsStream(): Flow<List<Task>> = itemDao.getAllItems()

    override fun getItemStream(id: Int): Flow<Task?> = itemDao.getItem(id)

    override suspend fun insertItem(item: Task) = itemDao.insert(item)

    override suspend fun deleteItem(item: Task) = itemDao.delete(item)

    override suspend fun updateItem(item: Task) = itemDao.update(item)
}
