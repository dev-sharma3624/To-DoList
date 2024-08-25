package com.example.to_dolist.Data

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

    fun getTaskById(id: Int): Flow<Task>{
        return taskDao.getTaskById(id)
    }
}