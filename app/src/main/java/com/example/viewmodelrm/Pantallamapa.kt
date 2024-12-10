package com.example.viewmodelrm

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.viewmodelrm.data.AppDatabase
import com.example.viewmodelrm.Pantalla
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.osmdroid.tileprovider.tilesource.XYTileSource

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
fun Pantallamapa(navController: NavHostController, database: AppDatabase) {


    TileSourceFactory.addTileSource(GoogleSat)

    var marcador = rememberMarkerState(
        geoPoint =  GeoPoint(29.141736, -13.507688)
    )

    val cameraState = rememberCameraState {
        geoPoint = GeoPoint(29.141736, -13.507688)
        zoom = 17.0 // optional, default is 5.0
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
        Marker(
            state = marcador,
            title = "IES Haría", // add title
            snippet = "Clase DAM2" // add snippet
        ){
            Column(
                modifier = Modifier
                    .size(100.dp)
                    .background(color = Color.Gray, shape = RoundedCornerShape(7.dp)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // setup content of info window
                Text(text = it.title)
                Text(text = it.snippet, fontSize = 10.sp)
            }
        }
    }
}

