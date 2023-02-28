package com.example.wellnessapp

import android.app.Application
import com.example.wellnessapp.data.AppContainer
import com.example.wellnessapp.data.AppDataContainer


class TaskApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}