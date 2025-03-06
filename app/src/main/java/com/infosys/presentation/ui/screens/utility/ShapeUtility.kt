package com.infosys.presentation.ui.screens.utility

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun roundShapeCorner(shape: Int = 12) = RoundedCornerShape(shape.dp)

@Composable
fun roundShapeCorner(tS: Int, tE: Int, bS: Int, bE: Int) =
    RoundedCornerShape(topStart = tS.dp, topEnd = tE.dp, bottomStart = bS.dp, bottomEnd = bE.dp)