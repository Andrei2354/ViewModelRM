package com.example.viewmodelrm.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
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
                Grupo(typeGrupo = "Restaurante"),
                Grupo(typeGrupo = "Playa"),
                Grupo(typeGrupo = "Acuario"),
                Grupo(typeGrupo = "Parque")
            )

            taskDao.insertMarcador(
                Marcador(
                    titulo = "Playa del Jabliyo",
                    coordenadaX = 28.99302768657386,
                    coordenadaY =  -13.489908744612295,
                    grupoid = 2
                ),
                Marcador(
                    titulo = "Playa Cucharas",
                    coordenadaX = 28.99862964321656,
                    coordenadaY =  -13.48812776095318,
                    grupoid = 2
                ),
                Marcador(
                    titulo = "Playa Bastián",
                    coordenadaX = 28.992986562609968,
                    coordenadaY = -13.495383991854991,
                    grupoid = 2
                ),
                Marcador(
                    titulo = "Playa de los Charcos",
                    coordenadaX = 29.001728347647582,
                    coordenadaY = -13.48222202006685,
                    grupoid = 2
                ),
                Marcador(
                    titulo = "Aquarium Costa Teguise",
                    coordenadaX = 29.003872308504498,
                    coordenadaY = -13.487158043268526,
                    grupoid = 3
                ),
                Marcador(
                    titulo = "AQUAPARK Costa Teguise",
                    coordenadaX = 29.000549304593093,
                    coordenadaY = -13.5085808706577,
                    grupoid = 4
                ),
                Marcador(
                    titulo = "Restaurante La toscana",
                    coordenadaX = 28.994945854431183,
                    coordenadaY = -13.489294143279725,
                    grupoid = 1
                ),
                Marcador(
                    titulo = "Mar Azul Restaurante Grill",
                    coordenadaX = 29.00406527758763,
                    coordenadaY = -13.487095185078802,
                    grupoid = 1
                ),
                Marcador(
                    titulo = "Trattoria San Marco",
                    coordenadaX = 28.995895727942163,
                    coordenadaY = -13.499500433448288,
                    grupoid = 1
                ),
                Marcador(
                    titulo = "Bar Moon",
                    coordenadaX = 29.00086039337344,
                    coordenadaY =-13.49003791298955,
                    grupoid = 1
                ),
                Marcador(
                    titulo = "Charco Vivo",
                    coordenadaX = 28.961879942924053,
                    coordenadaY = -13.544303551941955,
                    grupoid = 1
                ),
                Marcador(
                    titulo = "Burger King",
                    coordenadaX = 28.96195443443224,
                    coordenadaY = -13.538712191881555,
                    grupoid = 1
                )
            )
        }
    }
}