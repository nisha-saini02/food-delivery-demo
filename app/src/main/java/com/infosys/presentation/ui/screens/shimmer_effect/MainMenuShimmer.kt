package com.infosys.presentation.ui.screens.shimmer_effect

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.infosys.presentation.ui.screens.utility.roundShapeCorner

@Composable
fun MainMenuShimmer(brush: Brush) {
    Column(modifier = Modifier.padding(8.dp)) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(222.dp)
                .background(brush = brush, shape = roundShapeCorner(8))
        )
    }
}

@Composable
fun MainMenuHorizontalShimmer(brush: Brush) {
    Column(modifier = Modifier.padding(4.dp)) {
        Spacer(
            modifier = Modifier
                .size(width = 100.dp, height = 110.dp)
                .background(brush = brush, shape = roundShapeCorner(8))
        )
    }
}