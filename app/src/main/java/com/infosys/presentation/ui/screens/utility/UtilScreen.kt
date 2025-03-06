package com.infosys.presentation.ui.screens.utility

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.infosys.theme.Transparent
import com.infosys.theme.Typography
import com.infosys.theme.White
import com.infosys.theme.Yellow

@Composable
fun OutlineTextBodyMedium(
    text: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .border(BorderStroke(3.dp, Yellow), roundShapeCorner(30)),
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        text,
        onValueChange = {
            onValueChange.invoke(it)
        },
        textStyle = Typography.bodyMedium.copy(textAlign = textAlign),
        modifier = modifier,
        visualTransformation = visualTransformation,
        singleLine = singleLine,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Transparent,
            unfocusedBorderColor = Transparent,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType
        ),
    )
}

@Composable
fun OutlineTextBodySmall(
    text: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    textAlign: TextAlign = TextAlign.Start,
    hint: String = "",
    label: String = "",
    textColor: Color = White,
    hintColor: Color = White,
    borderColor: Color = Transparent,
    maxLength: Int = Int.MAX_VALUE,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        text,
        onValueChange = {
            if (it.length <= maxLength) onValueChange.invoke(it)
        },
        textStyle = Typography.bodySmall.copy(textAlign = textAlign),
        visualTransformation = visualTransformation,
        singleLine = singleLine,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType
        ),
        label = {
            TextHeadlineSmall(label, color = hintColor)
        },
        placeholder = {
            TextHeadlineSmall(hint, color = hintColor)
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun EditTextBodyMedium(
    text: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier
        .fillMaxWidth(),
    hint: String = "",
    textColor: Color = White,
    hintColor: Color = White,
    borderColor: Color = Transparent,
    maxLength: Int = Int.MAX_VALUE,
    onValueChange: (String) -> Unit
) {
    TextField(
        text,
        onValueChange = {
            if (it.length <= maxLength) onValueChange.invoke(it)
        },
        textStyle = Typography.bodyMedium.copy(textAlign = textAlign),
        modifier = modifier,
        visualTransformation = visualTransformation,
        singleLine = singleLine,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType
        ),
        placeholder = {
            TextHeadlineSmall(hint, color = hintColor)
        }
    )
}