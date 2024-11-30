package ru.clonsaldafon.todoapp.presentation.main

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.clonsaldafon.todoapp.R
import ru.clonsaldafon.todoapp.appComponent
import ru.clonsaldafon.todoapp.data.model.TaskEntity
import ru.clonsaldafon.todoapp.data.model.TaskState
import ru.clonsaldafon.todoapp.databinding.FragmentMainBinding
import ru.clonsaldafon.todoapp.di.viewModel.ViewModelFactory
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding: FragmentMainBinding by viewBinding()

    private val adapter = TasksAdapter(
        ::onTaskStateChangeClick
    )

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.tasksRecycler) {
            adapter = this@MainFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.tasks.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.buttonCreateTask.setOnClickListener {
            val destination = MainFragmentDirections.actionMainFragmentToCreateNewTaskFragment()
            findNavController().navigate(destination)
        }
    }

    private fun onTaskStateChangeClick(task: TaskEntity) {
        val states = TaskState.entries.map { it.name }.toTypedArray()
        var index = states.indexOf(task.state.name)
        
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Select new state")
            .setNeutralButton("Cancel") { _, _ ->  }
            .setPositiveButton("Ok") { dialog, newState ->
                val taskState = TaskState.values().get(index)
                viewModel.changeTaskState(task, taskState)
            }
            .setSingleChoiceItems(states, states.indexOf(task.state.name)) { _, newState ->
                index = newState
            }
            .show()
    }

}