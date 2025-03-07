package com.infosys.presentation.ui.screens.map

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.infosys.presentation.viewmodel.LocalViewModel
import com.infosys.theme.Orange

@SuppressLint("UnrememberedMutableState")
@Composable
fun OrderDetailScreen(cartLocalViewModel: LocalViewModel) {
    val order = cartLocalViewModel.fetchOrder.collectAsState().value

    val source = LatLng(30.7405083,76.6749134)
    val destination = LatLng(order.data?.destinationLat!!,order.data.destinationLong!!)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(source, 16f)
    }
    val uiSettings by remember {
        mutableStateOf(MapUiSettings(zoomControlsEnabled = true))
    }
    val properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }

    Box(Modifier.fillMaxSize()) {

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = properties,
            uiSettings = uiSettings
        ) {
            Marker(
                state = MarkerState(position = source),
                title = "Store"
            )
            Marker(
                state = MarkerState(position = destination),
                title = "Your location"
            )
            Polyline(
                listOf(source, destination),
                color = Orange
            )
        }
    }
}