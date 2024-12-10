package com.example.viewmodelrm

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.viewmodelrm.data.AppDatabase

@Composable
fun TaskApp(database: AppDatabase) {
    val navController = rememberNavController()

    // Configurar navegación
    AppNavigation(navController, database)
}

@Composable
fun AppNavigation(navController: NavHostController, database: AppDatabase) {
    NavHost(navController = navController, startDestination = "mapa") {
        composable("mapa") { Pantallamapa(navController, database) }
        composable("agregar") { Pantalla(navController, database) }
    }
}