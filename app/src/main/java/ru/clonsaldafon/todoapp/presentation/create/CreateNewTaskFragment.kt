package ru.clonsaldafon.todoapp.presentation.create

import android.content.Context
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import ru.clonsaldafon.todoapp.DATE_FORMAT
import ru.clonsaldafon.todoapp.R
import ru.clonsaldafon.todoapp.SEC_TO_MS
import ru.clonsaldafon.todoapp.WEEK
import ru.clonsaldafon.todoapp.appComponent
import ru.clonsaldafon.todoapp.databinding.FragmentCreateTaskBinding
import ru.clonsaldafon.todoapp.di.viewModel.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class CreateNewTaskFragment : Fragment(R.layout.fragment_create_task) {

    private val binding: FragmentCreateTaskBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: CreateNewTaskViewModel by viewModels { viewModelFactory }

    private val dateRangePicker = MaterialDatePicker.Builder.dateRangePicker()
        .setTitleText("Select dates")
        .setSelection(
            androidx.core.util.Pair(
                MaterialDatePicker.todayInUtcMilliseconds(),
                MaterialDatePicker.todayInUtcMilliseconds() + WEEK * SEC_TO_MS
            )
        )
        .build()

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newTaskToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.newTaskButtonPickDate.setOnClickListener {
            dateRangePicker.show(childFragmentManager, dateRangePicker::class.java.simpleName)
        }

        dateRangePicker.addOnPositiveButtonClickListener {
            onDateIntervalPicked(it)
        }

        binding.newTaskToolbar.setOnMenuItemClickListener {
            onFormReady()
        }
    }

    private fun onFormReady(): Boolean {
        val isFormValid = viewModel.validateForm(
            binding.newTaskTitle.editText?.text.toString(),
            binding.newTaskDescription.editText?.text.toString()
        )

        if (isFormValid) {
            viewModel.createTask()
            findNavController().popBackStack()
        } else {
            Snackbar
                .make(binding.root, "Fill out of the gaps", Snackbar.LENGTH_LONG)
                .show()
        }

        return isFormValid
    }

    private fun onDateIntervalPicked(it: androidx.core.util.Pair<Long, Long>) {
        viewModel.saveTimeInterval(it.first, it.second)
        val formatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
        val startDate = formatter.format(it.first)
        val endDate = formatter.format(it.second)
        val showDate = SpannableStringBuilder()
            .append(startDate)
            .append(" to ")
            .append(endDate)
        binding.newTaskDateInterval.editText?.text = showDate
    }

}