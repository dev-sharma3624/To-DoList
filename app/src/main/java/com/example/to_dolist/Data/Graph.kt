package com.example.to_dolist.Data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

object Graph {

    lateinit var database: TaskDatabase

    val taskRepository by lazy {
        TaskRepository(taskDao = database.taskDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, TaskDatabase::class.java, "task.db").build()
    }
}