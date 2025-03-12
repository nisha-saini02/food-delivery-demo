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
fun OrderShimmer(brush: Brush) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(120.dp)
                .background(brush = brush, shape = roundShapeCorner())
        )
    }
}