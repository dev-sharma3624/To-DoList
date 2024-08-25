package com.example.to_dolist.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_dolist.Data.Graph
import com.example.to_dolist.Data.Task
import com.example.to_dolist.Data.TaskRepository
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

}