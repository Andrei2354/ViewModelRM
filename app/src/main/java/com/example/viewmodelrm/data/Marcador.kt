package com.example.viewmodelrm.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "marcador") //Por defecto usa el nombre user
data class Marcador(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titulo: String,
    val sniped: String,
    val CoordenadaY: Double,
    val CoordenadaX: Double,
    val isCompleted: Boolean = false
)