package com.example.ordenhadiaria.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ordenhadiaria.BarChartApplication
import com.example.ordenhadiaria.R
import com.example.ordenhadiaria.data.ItemDao
import com.example.ordenhadiaria.data.RemoteDataSource
import com.example.ordenhadiaria.data.model.Ordenha
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BarChartActivity(
    private val remoteDataSource: RemoteDataSource,
    private val itemDao: ItemDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    init {
        fetchItems()
    }

    val ordenha: LiveData<List<Ordenha>> = itemDao.getLastFive()
    val topFiveItems: LiveData<List<Ordenha>> = itemDao.getFiveTop()
    val downFiveItems: LiveData<List<Ordenha>> = itemDao.getFiveDown()

    val total: LiveData<Float> = itemDao.getTotal()
    val max: LiveData<Float> = itemDao.getMax()
    val min: LiveData<Float> = itemDao.getMin()
    val avg: LiveData<Float> = itemDao.getAverage()

    fun fetchItems() {
        viewModelScope.launch(dispatcher) {
            remoteDataSource.fetch().map {
                    itemDao.insert(it)
                }
        }
    }

    companion object {

        fun create(application: Application): BarChartActivity {
            val credentials =
                GoogleCredential
                    .fromStream(application.resources.openRawResource(R.raw.credentials))

            val remoteDataSource = RemoteDataSource(credentials)
            val itemDao = (application as BarChartApplication).getAppDataBase().itemDao()
            return BarChartActivity(remoteDataSource, itemDao)
        }
    }
}
