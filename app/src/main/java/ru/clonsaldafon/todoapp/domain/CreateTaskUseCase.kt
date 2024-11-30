package ru.clonsaldafon.todoapp.domain

import ru.clonsaldafon.todoapp.data.model.TaskEntity
import ru.clonsaldafon.todoapp.data.model.TaskState
import ru.clonsaldafon.todoapp.data.repository.TasksRepository
import javax.inject.Inject

interface CreateTaskUseCase {

    suspend operator fun invoke(
        title: String,
        description: String,
        startTime: Long,
        endTime: Long
    )

}

class CreateTaskUseCaseImpl @Inject constructor(
    private val repository: TasksRepository
) : CreateTaskUseCase {

    override suspend fun invoke(
        title: String,
        description: String,
        startTime: Long,
        endTime: Long
    ) =
        repository.createNewTask(
            TaskEntity(
                title = title,
                description = description,
                startTime = startTime,
                endTime = endTime,
                state = TaskState.TODO
            )
        )

}