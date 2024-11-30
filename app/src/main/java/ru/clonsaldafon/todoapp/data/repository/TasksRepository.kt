package ru.clonsaldafon.todoapp.data.repository

import kotlinx.coroutines.flow.Flow
import ru.clonsaldafon.todoapp.data.db.TasksDAO
import ru.clonsaldafon.todoapp.data.model.TaskEntity
import ru.clonsaldafon.todoapp.data.model.TaskState
import javax.inject.Inject

interface TasksRepository {
    val tasksFlow: Flow<List<TaskEntity>>
    suspend fun updateTaskState(task: TaskEntity, state: TaskState)
    suspend fun createNewTask(task: TaskEntity)
}

class TasksRepositoryImpl @Inject constructor(
    private val dao: TasksDAO
) : TasksRepository {

    override val tasksFlow: Flow<List<TaskEntity>>
        get() = dao.getAllTasks()

    override suspend fun updateTaskState(task: TaskEntity, state: TaskState) {
        val copy = task.copy(
            state = state
        )
        dao.upsertTask(copy)
    }

    override suspend fun createNewTask(task: TaskEntity) {
        dao.upsertTask(task)
    }

}