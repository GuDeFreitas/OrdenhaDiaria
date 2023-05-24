package com.example.ordenhadiaria

import java.io.Serializable

data class Ordenha(
    val stringID : String,
    val totalAnimals : String,
    val firstMilking : String,
    val secondMilking : String,
    val average : String,
    val totalPerDay : String,
    val date : String,
):Serializable
