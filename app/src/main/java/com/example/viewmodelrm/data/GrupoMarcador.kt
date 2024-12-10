package com.example.viewmodelrm.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity
data class GrupoMarcador(
    @Embedded val marcador: Marcador,
    @Relation(
        parentColumn = "id",
        entityColumn = "idGrupo"
    )
    val grupoMarcadores: List<Grupo>
)