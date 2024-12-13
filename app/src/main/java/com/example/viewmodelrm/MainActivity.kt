package com.example.viewmodelrm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viewmodelrm.data.AppDatabase
import com.example.viewmodelrm.ui.theme.ViewModelRMTheme
import androidx.compose.foundation.layout.padding
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viewmodelrm.view.Pantallamapa
import com.example.viewmodelrm.viewmodel.MarcadorViewModel
import com.example.viewmodelrm.viewmodel.ViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var database: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(this)
        val taskdao = database.taskDao()
        enableEdgeToEdge()
        setContent {
            ViewModelRMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val viewModel: MarcadorViewModel = viewModel(factory = ViewModelFactory(taskdao))


                    Pantallamapa(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}