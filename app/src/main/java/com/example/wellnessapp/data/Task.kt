package com.example.wellnessapp.data

/*
* Filename: Task.kt
* Author: Krithika Kasaragod
* */

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    val name: String,

    @ColumnInfo(name = "is_completed")
    var isDone: Boolean = false

)