package com.example.viewmodelrm.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "types")
data class Grupo(
    @PrimaryKey(autoGenerate = true) val idType: Int = 0,
    val typetask: String,
    val isCompleted: Boolean = false
)