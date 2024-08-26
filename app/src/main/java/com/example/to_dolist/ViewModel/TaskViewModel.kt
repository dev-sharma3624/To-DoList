package com.example.to_dolist.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_dolist.Data.Graph
import com.example.to_dolist.Data.Task
import com.example.to_dolist.Data.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TaskViewModel(
    private val taskRepository: TaskRepository = Graph.taskRepository
): ViewModel() {

    lateinit var getAllTasks: Flow<List<Task>>

    init {
        viewModelScope.launch {
            getAllTasks = taskRepository.getAllTasks()
        }
    }

    fun addTask(newTask: Task){
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.addTask(newTask)
        }
    }

    fun deleteTask(deletedTask: Task){
        viewModelScope.launch {
            taskRepository.deleteTask(deletedTask)
        }
    }

    fun updateTask(updatedTask: Task){
        viewModelScope.launch {
            taskRepository.updateTask(updatedTask)
        }
    }

    fun getTaskById(id: Long): Flow<Task>{
        return taskRepository.getTaskById(id)
    }

}