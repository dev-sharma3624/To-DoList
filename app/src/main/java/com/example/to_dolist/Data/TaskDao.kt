package com.example.to_dolist.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TaskDao {

    @Query("select * from taskTable")
    abstract fun getAllTasks(): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertTask(newTask: Task)

    @Update
    abstract suspend fun updateTask(updatedTask: Task)

    @Delete
    abstract suspend fun deleteTask(deletedWish: Task)

    @Query("select * from taskTable where id = :id")
    abstract fun getTaskById(id: Long): Flow<Task>
}