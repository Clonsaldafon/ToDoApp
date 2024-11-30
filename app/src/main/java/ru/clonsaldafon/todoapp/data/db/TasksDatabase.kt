package ru.clonsaldafon.todoapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.clonsaldafon.todoapp.data.model.TaskEntity

@Database(
    entities = [
        TaskEntity::class
    ],
    version = 1
)
abstract class TasksDatabase : RoomDatabase() {

    abstract val tasksDao: TasksDAO

}