package com.infosys.presentation.ui.screens.utility

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.infosys.theme.Black
import com.infosys.theme.Typography
import com.infosys.theme.Yellow

@Composable
fun ButtonCr(
    bgColor: Color = Yellow,
    corner: Int = 30,
    modifier: Modifier = Modifier
        .wrapContentSize()
        .padding(vertical = 6.dp, horizontal = 12.dp),
    text: String,
    textColor: Color = Black,
    clickEvent: () -> Unit
) {
    Button(
        onClick = {
            clickEvent.invoke()
        },
        colors = ButtonDefaults.buttonColors(bgColor),
        shape = roundShapeCorner(corner),
        modifier = modifier,
    ) {
        Text(
            style = Typography.titleMedium,
            color = textColor,
            text = text,
            modifier = modifier
        )
    }
}