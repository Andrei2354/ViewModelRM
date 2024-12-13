package com.example.viewmodelrm.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {
    @Transaction @Query("SELECT * FROM marcador") fun getAllgrupoMarcador(): Flow<List<GrupoMarcador>>
    @Insert
    suspend fun insertMarcador(vararg  marcador: Marcador)
    @Insert
    suspend fun insertGrupo(vararg grupo: Grupo)
    @Query("SELECT * FROM types")
    suspend fun getAllGrupos(): List<Grupo>

    @Query("SELECT * FROM marcador")
    suspend fun getAllMarcadores(): List<Marcador>
}