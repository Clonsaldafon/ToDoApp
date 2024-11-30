package ru.clonsaldafon.todoapp

import android.app.Application
import android.content.Context
import ru.clonsaldafon.todoapp.di.AppComponent
import ru.clonsaldafon.todoapp.di.DaggerAppComponent

class MyApplication: Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        super.onCreate()
    }

}

val Context.appComponent: AppComponent
    get() = when(this) {
        is MyApplication -> this.appComponent
        else -> applicationContext.appComponent
    }