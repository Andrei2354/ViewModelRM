package com.example.viewmodelrm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Database(entities = [Marcador::class, Grupo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null
        @OptIn(DelicateCoroutinesApi::class)

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "task_database"
                ).build()
                INSTANCE = instance

                GlobalScope.launch {
                    CargarDatos(instance.taskDao())
                }
                instance
            }
        }

        private suspend fun CargarDatos(taskDao: TaskDao) {

                    taskDao.insertGrupo(
                        Grupo(typeGrupo = "Grupo A"),
                        Grupo(typeGrupo = "Grupo B")
                    )

                    taskDao.insertMarcador(
                        Marcador(
                            titulo = "Marcador 1",
                            sniped = "Descripción 1",
                            coordenadaX = 10.5,
                            coordenadaY = -5.5
                        ),
                        Marcador(
                            titulo = "Marcador 2",
                            sniped = "Descripción 2",
                            coordenadaX = 15.0,
                            coordenadaY = 20.0
                        )
                    )
        }
    }
}