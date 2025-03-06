package com.infosys.presentation.ui.screens.utility

import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import com.infosys.theme.Gray
import com.infosys.theme.White
import com.infosys.theme.Yellow

@Composable
fun Switch(flag: Boolean, onCheckedChange: (Boolean) -> Unit) {
    androidx.compose.material3.Switch(
        checked = flag,
        onCheckedChange = { onCheckedChange.invoke(it) },
        colors = SwitchDefaults.colors(
            checkedThumbColor = White,
            checkedTrackColor = Yellow,
            uncheckedThumbColor = White,
            uncheckedTrackColor = Gray,
        )
    )
}