package com.example.viewmodelrm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.viewmodelrm.data.AppDatabase

class MainActivity : ComponentActivity() {
    private lateinit var database: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = AppDatabase.getDatabase(this)

        setContent {
            Pantallamapa(database)
        }
    }
}