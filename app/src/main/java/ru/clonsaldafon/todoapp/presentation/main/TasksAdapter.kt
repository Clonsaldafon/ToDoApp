package ru.clonsaldafon.todoapp.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.clonsaldafon.todoapp.DATE_FORMAT
import ru.clonsaldafon.todoapp.data.model.TaskEntity
import ru.clonsaldafon.todoapp.databinding.ItemTaskBinding
import java.text.SimpleDateFormat
import java.util.Locale

class TasksAdapter(
    private val onTaskStateChangeClick: (TaskEntity) -> Unit
) : ListAdapter<TaskEntity, TasksAdapter.TaskViewHolder>(TaskDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(onTaskStateChangeClick, binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TaskViewHolder(
        private val onTaskStateChangeClick: (TaskEntity) -> Unit,
        private val binding: ItemTaskBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(task: TaskEntity) = with(binding) {
            val formatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
            itemTaskTitle.text = task.title
            itemTaskDescription.text = task.description
            itemTaskStartTime.text = formatter.format(task.startTime)
            itemTaskEndTime.text = formatter.format(task.endTime)
            itemTaskStatus.text = task.state.name
            itemTaskStatus.setOnClickListener {
                onTaskStateChangeClick(task)
            }
        }

    }

    private class TaskDiffUtil : DiffUtil.ItemCallback<TaskEntity>() {

        override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean =
            oldItem == newItem

    }

}