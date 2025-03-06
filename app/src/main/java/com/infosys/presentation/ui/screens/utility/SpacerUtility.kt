package com.infosys.presentation.ui.screens.utility

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Spacer(size: Int = 16) =
    androidx.compose.foundation.layout.Spacer(modifier = Modifier.size(size.dp))