package com.infosys.presentation.ui.screens.utility

import android.net.Uri
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.infosys.R

@Composable
fun Image(@DrawableRes id: Int, modifier: Modifier = Modifier.fillMaxWidth()) {
    androidx.compose.foundation.Image(
        painter = painterResource(id),
        contentDescription = "Logo",
        modifier = modifier
    )
}

@Composable
fun Image(@DrawableRes id: Int, modifier: Modifier = Modifier.fillMaxWidth(), clickEvent: () -> Unit) {
    androidx.compose.foundation.Image(
        painter = painterResource(id),
        contentDescription = "Logo",
        modifier = modifier.clickable { clickEvent.invoke() }
    )
}

@Composable
fun LoadImage(url: String,
              modifier: Modifier = Modifier
                  .width(50.dp)
                  .height(50.dp)
){
    AsyncImage(
        model = url,
        contentDescription = "Image Description",
        modifier = modifier.clip(roundShapeCorner()),
        contentScale = ContentScale.Crop,
        placeholder = painterResource(R.drawable.placeholder),
        error = painterResource(R.drawable.no_image_found),
        onError = { exception -> Log.e("AsyncImage", "Error loading image: ${exception.result}") }
    )
}

@Composable
fun LoadImage(url: String,
              modifier: Modifier = Modifier
                  .width(50.dp)
                  .height(50.dp),
              clickEvent: () -> Unit
){
    AsyncImage(
        model = url,
        contentDescription = "Image Description",
        modifier = modifier.clip(roundShapeCorner(5)).clickable { clickEvent.invoke() },
        contentScale = ContentScale.Crop,
        placeholder = painterResource(R.drawable.placeholder),
        error = painterResource(R.drawable.no_image_found),
        onError = { exception -> Log.e("AsyncImage", "Error loading image: ${exception.result}") }
    )
}

@Composable
fun LoadImage(uri: Uri?,
              modifier: Modifier = Modifier.size(100.dp),
              clickEvent: () -> Unit
){
    AsyncImage(
        model = uri,
        contentDescription = "Image Description",
        modifier = modifier.clip(roundShapeCorner(50)).clickable { clickEvent.invoke() },
        contentScale = ContentScale.Crop,
        placeholder = painterResource(R.drawable.placeholder),
        error = painterResource(R.drawable.no_image_found),
        onError = { exception -> Log.e("AsyncImage", "Error loading image: ${exception.result}") }
    )
}