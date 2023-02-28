package com.example.wellnessapp.data

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

    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Task>>

}