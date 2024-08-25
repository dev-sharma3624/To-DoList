package com.example.to_dolist

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.to_dolist.Data.Graph
import com.example.to_dolist.ViewModel.TaskViewModel

class ToDoListApp: Application() {
    override fun onCreate() {
        super.onCreate()

        val tag = "TEST"

        Log.d(tag, "Inside Application Class")

        Graph.provide(this)

    }
}