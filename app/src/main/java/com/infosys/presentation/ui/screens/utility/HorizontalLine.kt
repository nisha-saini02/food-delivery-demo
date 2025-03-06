package com.infosys.presentation.ui.screens.utility

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.infosys.theme.White

@Composable
fun HorizontalLine(height: Int = 1, color: Color = White) =
    androidx.compose.foundation.layout.Spacer(
        modifier = Modifier
            .height(height.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(color)
    )