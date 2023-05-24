package com.example.ordenhadiaria


import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader

class MainActivity : AppCompatActivity() {

    lateinit var barChart: BarChart

    private val arrayItem : ArrayList<Ordenha> = arrayListOf()
    private val arrayBarEntry : ArrayList<BarEntry> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        barChart = findViewById(R.id.bar_chart)

        readCSVFile()
        convertData(arrayItem)
        val barDataSet = BarDataSet(arrayBarEntry, "list")

        barDataSet.setColors (ColorTemplate .MATERIAL_COLORS,  255)

        barDataSet.valueTextColor = Color.BLACK

        val barData = BarData(barDataSet)

        barChart.setFitBars(true)

        barChart.data = barData

        barChart.description.text = "Bar Chart"

        barChart.animateY(2000)

        barChart.barData.barWidth = 20f

    }

    private fun readCSVFile() {

        val bufferReader = BufferedReader(assets.open( "ControleLeiteiro.csv").reader())
        val csvParser = CSVParser.parse(
            bufferReader,
            CSVFormat.DEFAULT
        )
        val list = mutableListOf<Ordenha>()
        csvParser.forEach {
            it?.let {
                val data = Ordenha(
                    stringID = it.get(0),
                    totalAnimals = it.get(1),
                    firstMilking = it.get(2).replace(',','.'),
                    secondMilking = it.get(3).replace(',','.'),
                    average = it.get (4),
                    totalPerDay = it.get(5).replace(',','.'),
                    date = it.get(6),
                )
                arrayItem.add(data)
            }
        }
        arrayItem.removeFirst()
    }
    private fun convertData(list: ArrayList<Ordenha>){
        list.forEach {
            val barEntry = BarEntry(it.secondMilking.toFloat(), it.totalPerDay.toFloat())
            arrayBarEntry.add(barEntry)
        }
    }
}

