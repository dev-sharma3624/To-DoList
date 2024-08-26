package com.example.to_dolist.Data

import android.util.Log
import kotlinx.coroutines.flow.Flow

class TaskRepository (private val taskDao: TaskDao) {

    fun getAllTasks(): Flow<List<Task>>{
        return taskDao.getAllTasks()
    }

    suspend fun addTask(newTask:  Task){
        taskDao.insertTask(newTask)
    }

    suspend fun updateTask(updatedTask: Task){
        taskDao.updateTask(updatedTask)
    }

    suspend fun deleteTask(deletedTask: Task){
        taskDao.deleteTask(deletedTask)
    }

    fun getTaskById(id: Long): Flow<Task>{
        return taskDao.getTaskById(id)
    }
}