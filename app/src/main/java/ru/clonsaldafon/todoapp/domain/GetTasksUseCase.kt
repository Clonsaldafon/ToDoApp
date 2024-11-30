package ru.clonsaldafon.todoapp.domain

import kotlinx.coroutines.flow.Flow
import ru.clonsaldafon.todoapp.data.model.TaskEntity
import ru.clonsaldafon.todoapp.data.repository.TasksRepository
import javax.inject.Inject

interface GetTasksUseCase {

    suspend operator fun invoke(): Flow<List<TaskEntity>>

}

class GetTasksUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : GetTasksUseCase {

    override suspend fun invoke(): Flow<List<TaskEntity>> =
        repository.tasksFlow

}