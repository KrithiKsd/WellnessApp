package com.example.wellnessapp.data
/*
* Filename: TaskDao.kt
* Author: Krithika Kasaragod
* */

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Task)

    @Update
    suspend fun update(item: Task)

    @Delete
    suspend fun delete(item: Task)

    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Task>

    @Query("SELECT * from items")
    fun getAllItems(): Flow<List<Task>>

}