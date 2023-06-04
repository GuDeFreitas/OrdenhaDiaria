package com.example.ordenhadiaria.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ordenhadiaria.BarChartApplication
import com.example.ordenhadiaria.data.model.Ordenha

@Database(entities = [Ordenha::class], version = 4)
abstract class AppDataBase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
}

