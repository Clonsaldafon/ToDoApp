package ru.clonsaldafon.todoapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import ru.clonsaldafon.todoapp.di.viewModel.ViewModelModule
import ru.clonsaldafon.todoapp.presentation.create.CreateNewTaskFragment
import ru.clonsaldafon.todoapp.presentation.main.MainFragment
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class
    ]
)
@Singleton
interface AppComponent {

    fun inject(fragment: MainFragment)
    fun inject(fragment: CreateNewTaskFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }

}

@Module(
    includes = [
        ViewModelModule::class,
        AppBindsModule::class
    ]
)
class AppModule