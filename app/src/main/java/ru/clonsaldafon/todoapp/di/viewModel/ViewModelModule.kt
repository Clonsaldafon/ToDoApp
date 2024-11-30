package ru.clonsaldafon.todoapp.di.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.clonsaldafon.todoapp.presentation.create.CreateNewTaskViewModel
import ru.clonsaldafon.todoapp.presentation.main.MainViewModel

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateNewTaskViewModel::class)
    fun bindCreateNewTaskViewModel(viewModel: CreateNewTaskViewModel): ViewModel

}