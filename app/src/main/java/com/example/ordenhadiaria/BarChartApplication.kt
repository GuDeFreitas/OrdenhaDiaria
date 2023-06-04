package com.example.ordenhadiaria

import android.app.Application
import androidx.room.Room
import com.example.ordenhadiaria.data.AppDataBase

class BarChartApplication: Application() {

    private lateinit var dataBase: AppDataBase

    override fun onCreate() {
        super.onCreate()

        dataBase = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "graph-database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    fun getAppDataBase(): AppDataBase{
        return dataBase
    }
}