package com.example.ordenhadiaria


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser

import java.io.BufferedReader


class MainActivity : AppCompatActivity() {

    private val arrayItem : ArrayList<Ordenha> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        readCSVFile()
    }

    private fun readCSVFile() {
        val textView = findViewById<TextView>(R.id.text_del)
        val bufferReader = BufferedReader(assets.open( "data.csv").reader())
        val csvParser = CSVParser.parse(
            bufferReader,
            CSVFormat.DEFAULT
        )
        val list = mutableListOf<Ordenha>()
        csvParser.forEach {
            it?.let {
                val data = Ordenha(
                    id = it.get(0),
                    microchip = it.get(1),
                    numeroDoAnimal = it.get(2),
                    nome = it.get(3),
                    dataDoParto = it.get (4),
                    baia = it.get(5),
                    primeiraOrdenha = it.get(5),
                    segundaOrdenha = it.get(6),
                    total = it.get(7),
                    dataDoControle = it.get(8),
                    del = it.get(9),
                    obs = it.get(10),
                )
                arrayItem.add(data)
            }
        }
        println(arrayItem)
    }
}
