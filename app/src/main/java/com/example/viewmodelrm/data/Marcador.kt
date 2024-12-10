package com.example.viewmodelrm.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "marcador")
data class Marcador(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titulo: String,
    val sniped: String,
    val coordenadaY: Double,
    val coordenadaX: Double,
    val isCompleted: Boolean = false
)