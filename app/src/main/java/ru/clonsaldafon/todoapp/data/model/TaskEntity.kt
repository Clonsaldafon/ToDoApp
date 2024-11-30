package ru.clonsaldafon.todoapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.clonsaldafon.todoapp.data.model.TaskEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val startTime: Long,
    val endTime: Long,
    val state: TaskState
) {

    companion object {
        const val TABLE_NAME = "tasks"
    }

}