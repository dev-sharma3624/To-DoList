package com.example.to_dolist.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "taskTable")
data class Task(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "isCompleted")
    var isCompleted: Boolean = false,

    @ColumnInfo(name = "taskStatement")
    var taskStatement: String = ""
)

