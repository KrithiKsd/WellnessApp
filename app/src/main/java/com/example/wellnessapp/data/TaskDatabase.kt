package com.example.wellnessapp.data

/*
* Filename: TaskDatabase.kt
* Author: Krithika Kasaragod
* */
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase(){
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var Instance: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, TaskDatabase::class.java, "item_database")
                   // .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}