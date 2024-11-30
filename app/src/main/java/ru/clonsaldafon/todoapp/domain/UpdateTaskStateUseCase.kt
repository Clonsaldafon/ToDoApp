package ru.clonsaldafon.todoapp.domain

import ru.clonsaldafon.todoapp.data.model.TaskEntity
import ru.clonsaldafon.todoapp.data.model.TaskState
import ru.clonsaldafon.todoapp.data.repository.TasksRepository
import javax.inject.Inject

interface UpdateTaskStateUseCase {

    suspend operator fun invoke(task: TaskEntity, newState: TaskState)

}

class UpdateTaskStateUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : UpdateTaskStateUseCase {

    override suspend fun invoke(task: TaskEntity, newState: TaskState) =
        repository.updateTaskState(task, newState)

}