package com.example.viewmodelrm

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.viewmodelrm.data.AppDatabase
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.viewmodelrm.ui.theme.ViewModelRMTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.utsman.osmandcompose.DefaultMapProperties
import com.utsman.osmandcompose.Marker
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.ZoomButtonVisibility
import com.utsman.osmandcompose.rememberCameraState
import com.utsman.osmandcompose.rememberMarkerState
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import org.osmdroid.tileprovider.tilesource.XYTileSource
import com.example.viewmodelrm.data.GrupoMarcador
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.text.style.TextAlign

data class Tile(val x: Int, val y: Int, val zoomLevel: Int)

val GoogleSat = object : XYTileSource(
    "Google-Sat",
    0, 19, 256, ".png",
    arrayOf(
        "https://mt0.google.com",
        "https://mt1.google.com",
        "https://mt2.google.com",
        "https://mt3.google.com"
    )
) {
    fun getTileURLString(tile: Tile): String {
        return "${getBaseUrl()}/vt/lyrs=s&x=${tile.x}&y=${tile.y}&z=${tile.zoomLevel}"
    }
}

@Composable
fun Pantallamapa(database: AppDatabase) {
    val taskDao = database.taskDao()
    val scope = rememberCoroutineScope()
    var marcadores  by remember { mutableStateOf(listOf<GrupoMarcador>()) }

    LaunchedEffect(Unit) {
        scope.launch(Dispatchers.IO) {
            marcadores = taskDao.getAllgrupoMarcador()
        }
    }

    TileSourceFactory.addTileSource(GoogleSat)


    val cameraState = rememberCameraState {
        geoPoint = GeoPoint(28.992986562609960,  -13.495383991854991)
        zoom = 15.0 // optional, default is 5.0
    }

    // define properties with remember with default value
    var mapProperties by remember {
        mutableStateOf(DefaultMapProperties)
    }

    // setup mapProperties in side effect
    SideEffect {
        mapProperties = mapProperties
            //.copy(isTilesScaledToDpi = true)
            .copy(tileSources = TileSourceFactory.MAPNIK)
            .copy(isEnableRotationGesture = true)
            .copy(zoomButtonVisibility = ZoomButtonVisibility.NEVER)
    }

    OpenStreetMap(
        modifier = Modifier.fillMaxSize(),
        cameraState = cameraState,
        properties = mapProperties // add properties
    ){
        marcadores.forEach { elementos ->
            var marcador = rememberMarkerState(
                geoPoint =  GeoPoint(elementos.marcador.coordenadaX, elementos.marcador.coordenadaY)
            )
            var titulo = elementos.marcador.titulo
            elementos.grupoMarcadores.forEach { elementogrupo ->
                var snipe = elementogrupo.typeGrupo

                Marker(
                    state = marcador,
                    title = titulo, // add title
                    snippet = snipe // add snippet
                ){
                    Column(
                        modifier = Modifier
                            .size(100.dp)
                            .background(color = Color.Gray, shape = RoundedCornerShape(7.dp)),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // setup content of info window
                        Text(text = it.title, textAlign = TextAlign.Center)
                        Text(text = it.snippet, fontSize = 10.sp)
                    }
                }
            }
        }

    }
}

