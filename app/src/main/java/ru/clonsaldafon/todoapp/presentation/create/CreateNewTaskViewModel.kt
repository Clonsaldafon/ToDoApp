package ru.clonsaldafon.todoapp.presentation.create

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.clonsaldafon.todoapp.domain.CreateTaskUseCase
import javax.inject.Inject

class CreateNewTaskViewModel @Inject constructor(
    private val createTaskUseCase: CreateTaskUseCase
) : ViewModel() {

    private var title: String? = null
    private var description: String? = null
    private var startTime: Long = -1
    private var endTime: Long = -1

    fun saveTimeInterval(startTime: Long, endTime: Long) {
        this.startTime = startTime
        this.endTime = endTime
    }

    fun validateForm(title: String, description: String): Boolean {
        if (title.trim().isEmpty())
            return false
        this.title = title

        if (description.trim().isEmpty())
            return false
        this.description = description
        
        return startTime != -1L && endTime != -1L
    }

    fun createTask() {
        title ?: return
        description ?: return
        if (startTime == -1L || endTime == -1L) return

        CoroutineScope(Dispatchers.IO).launch {
            createTaskUseCase(
                title = requireNotNull(title),
                description = requireNotNull(description),
                startTime = startTime,
                endTime = endTime
            )
        }
    }

}