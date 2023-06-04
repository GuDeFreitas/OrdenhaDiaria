package com.example.ordenhadiaria.data


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ordenhadiaria.data.model.Ordenha

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (item: Ordenha)

    @Query("SELECT * FROM Ordenha ORDER BY rowIndex DESC LIMIT 10")
    fun getAll(): LiveData<List<Ordenha>>

    @Query("SELECT * FROM Ordenha ORDER BY rowIndex DESC LIMIT 5")
    fun getLastFive(): LiveData<List<Ordenha>>

    @Query("SELECT * FROM Ordenha ORDER BY total DESC LIMIT 5")
    fun getFiveTop(): LiveData<List<Ordenha>>

    @Query("SELECT * FROM Ordenha ORDER BY total ASC LIMIT 5")
    fun getFiveDown(): LiveData<List<Ordenha>>

    @Query("SELECT SUM(total) FROM Ordenha")
    fun getTotal(): LiveData<Float>

    @Query("SELECT AVG(total) FROM Ordenha")
    fun getAverage(): LiveData<Float>

    @Query("SELECT total FROM Ordenha ORDER BY total DESC LIMIT 1")
    fun getMax(): LiveData<Float>

    @Query("SELECT total FROM Ordenha ORDER BY total DESC LIMIT 1")
    fun getMin(): LiveData<Float>

}