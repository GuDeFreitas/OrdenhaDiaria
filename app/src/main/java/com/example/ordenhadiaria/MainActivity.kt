package com.example.ordenhadiaria


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.opencsv.CSVParser
import com.opencsv.CSVReaderHeaderAware
import org.apache.commons.csv.CSVFormat
import java.io.BufferedReader
import java.io.FileReader

class MainActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

                readCSVFile()
        }

        private fun readCSVFile() {
                val bufferReader = BufferedReader(assets.open("data.csv").reader())
                val csvParser = CSVParser.parse(
                        bufferReader,
                CSVFormat.DEFAULT
                )
                val list = mutableListOf<Ordenha>()
                csvParser.forEach {
                        it?.let {
                              Ordenha (

                                      )
                        }
                }
    }
}

/*data class Data(
        val total : String,
        val maximo : String,
        val minimo : String,
        val media: Float,
        val del : Float)*/


/*        val reader = CSVReaderHeaderAware(FileReader("data.csv"))
        val resultList = mutableListOf<Map<String, String>>()
        var line = reader.readMap()
        while (line != null) {
            resultList.add(line)
            line = reader.readMap()
        }
        Log.d("MainActivity", line.toString())
        Log.d("MainActivity", resultList.toString())
        Log.d("MainActivity", reader.toString())*/