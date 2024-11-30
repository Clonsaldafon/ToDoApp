package ru.clonsaldafon.todoapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.clonsaldafon.todoapp.data.db.TasksDAO
import ru.clonsaldafon.todoapp.data.db.TasksDatabase
import ru.clonsaldafon.todoapp.data.repository.TasksRepository
import ru.clonsaldafon.todoapp.data.repository.TasksRepositoryImpl
import ru.clonsaldafon.todoapp.domain.CreateTaskUseCase
import ru.clonsaldafon.todoapp.domain.CreateTaskUseCaseImpl
import ru.clonsaldafon.todoapp.domain.GetTasksUseCase
import ru.clonsaldafon.todoapp.domain.GetTasksUseCaseImpl
import ru.clonsaldafon.todoapp.domain.UpdateTaskStateUseCase
import ru.clonsaldafon.todoapp.domain.UpdateTaskStateUseCaseImpl
import javax.inject.Singleton

@Module
interface AppBindsModule {

    @Binds
    @Singleton
    fun bindGetTasksUseCase(useCase: GetTasksUseCaseImpl): GetTasksUseCase

    @Binds
    @Singleton
    fun bindUpdateTaskStateUseCase(useCase: UpdateTaskStateUseCaseImpl): UpdateTaskStateUseCase

    @Binds
    @Singleton
    fun bindCreateTaskUseCase(useCase: CreateTaskUseCaseImpl): CreateTaskUseCase

    @Binds
    @Singleton
    fun bindTasksRepository(repository: TasksRepositoryImpl): TasksRepository

    companion object {

        @Provides
        fun provideContext(app: Application): Context = app.applicationContext

        @Provides
        @Singleton
        fun provideDb(context: Context): TasksDatabase =
            Room.databaseBuilder(
                context,
                TasksDatabase::class.java,
                "tasks.db"
            ).build()

        @Provides
        @Singleton
        fun provideTasksDao(db: TasksDatabase): TasksDAO =
            db.tasksDao
    }

}