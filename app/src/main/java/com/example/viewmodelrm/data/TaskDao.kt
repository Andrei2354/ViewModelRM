package com.example.viewmodelrm.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update


@Dao
interface TaskDao {
    @Insert suspend fun insert()
    @Insert suspend fun delete()
    @Insert suspend fun update()
}