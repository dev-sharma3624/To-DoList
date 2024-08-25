package com.example.to_dolist.Data

import android.util.Log
import kotlinx.coroutines.flow.Flow

class TaskRepository (private val taskDao: TaskDao) {

    val tag = "TEST"

    fun getAllTasks(): Flow<List<Task>>{
        Log.d(tag, "Inside TaskRepository getAllTasks()")
        return taskDao.getAllTasks()
    }

    suspend fun addTask(newTask:  Task){
        Log.d(tag, "Inside TaskRepository addTask()")
        Log.d(tag, newTask.toString())
        taskDao.insertTask(newTask)
        Log.d(tag, "task added")
    }

    suspend fun updateTask(updatedTask: Task){
        Log.d(tag, "Inside TaskRepository updateTask()")
        taskDao.updateTask(updatedTask)
    }

    suspend fun deleteTask(deletedTask: Task){
        Log.d(tag, "Inside TaskRepository deleteTask()")
        taskDao.deleteTask(deletedTask)
    }

    fun getTaskById(id: Long): Flow<Task>{
        Log.d(tag, "Inside TaskRepository geTaskById()")
        return taskDao.getTaskById(id)
    }
}