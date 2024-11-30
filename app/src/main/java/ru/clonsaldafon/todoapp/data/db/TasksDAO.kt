package ru.clonsaldafon.todoapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import ru.clonsaldafon.todoapp.data.model.TaskEntity

@Dao
interface TasksDAO {

    @Upsert
    suspend fun upsertTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Query("SELECT * FROM ${TaskEntity.TABLE_NAME} ORDER BY endTime ASC")
    fun getAllTasks(): Flow<List<TaskEntity>>

}