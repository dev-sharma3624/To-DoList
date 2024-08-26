package com.example.to_dolist.Data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false
)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}