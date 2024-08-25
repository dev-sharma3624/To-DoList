package com.example.to_dolist.Data

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase

object Graph {
    val tag = "TEST"
    lateinit var database: TaskDatabase

    val taskRepository by lazy {
        TaskRepository(taskDao = database.taskDao())
    }

    fun provide(context: Context){
        Log.d(tag, "Entered provide function")
        database = Room.databaseBuilder(context, TaskDatabase::class.java, "task.db").build()
    }
}