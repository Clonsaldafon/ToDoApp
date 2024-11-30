package ru.clonsaldafon.todoapp.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.clonsaldafon.todoapp.data.model.TaskEntity
import ru.clonsaldafon.todoapp.data.model.TaskState
import ru.clonsaldafon.todoapp.domain.GetTasksUseCase
import ru.clonsaldafon.todoapp.domain.UpdateTaskStateUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val updateTaskStateUseCase: UpdateTaskStateUseCase
) : ViewModel() {

    private val _tasks = MutableLiveData<List<TaskEntity>>()
    val tasks: LiveData<List<TaskEntity>>
        get() = _tasks

    init {
        viewModelScope.launch {
            getTasksUseCase().collect {
                _tasks.postValue(it)
            }
        }
    }

    fun changeTaskState(task: TaskEntity, state: TaskState) {
        viewModelScope.launch {
            updateTaskStateUseCase(
                task = task,
                newState = state
            )
        }
    }

}