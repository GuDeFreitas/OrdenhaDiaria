package com.example.codechallengeordenha.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ordenha(
    @PrimaryKey
    val ID: String,
    val total: Float,
    val rowIndex: Int,
    val date: String,
)