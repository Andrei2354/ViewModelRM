package com.example.viewmodelrm.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update


@Dao
interface TaskDao {
    @Transaction @Query("SELECT * FROM marcador") fun getAllgrupoMarcador(): List<GrupoMarcador>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMarcador(vararg  marcador: Marcador)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGrupo(vararg grupo: Grupo)
}