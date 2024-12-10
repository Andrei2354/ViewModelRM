package com.example.viewmodelrm.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity
data class GrupoMarcador(
    @Embedded val task: Marcador,
    @Relation(
        parentColumn = "typeid",
        entityColumn = "idType"
    )
    val typelist: List<Grupo>
)