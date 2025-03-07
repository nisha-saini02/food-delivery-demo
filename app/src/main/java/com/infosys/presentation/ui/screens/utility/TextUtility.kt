package com.infosys.presentation.ui.screens.utility

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.infosys.theme.Black
import com.infosys.theme.Transparent
import com.infosys.theme.Typography
import com.infosys.theme.Yellow

@Composable
fun TextTitleMedium(text: String, modifier: Modifier = Modifier, color: Color = Black, maxLines: Int = 2, textAlign: TextAlign = TextAlign.Center) {
    Text(
        text = text,
        style = Typography.titleMedium,
        color = color.copy(alpha = 1f),
        modifier = modifier,
        maxLines = maxLines,
        textAlign = textAlign
    )
}

@Composable
fun TextTitleSmall(text: String, modifier: Modifier = Modifier, color: Color = Black, maxLines: Int = 2, textAlign: TextAlign = TextAlign.Center) {
    Text(
        text = text,
        style = Typography.titleSmall,
        color = color,
        modifier = modifier,
        maxLines = maxLines,
        textAlign = textAlign
    )
}

@Composable
fun TextTitleLarge(text: String, modifier: Modifier = Modifier, color: Color = Black, maxLines: Int = 2, textAlign: TextAlign = TextAlign.Center, clickEvent: () -> Unit) {
    Text(
        text = text,
        style = Typography.titleLarge,
        color = color,
        modifier = modifier.clickable { clickEvent.invoke() },
        maxLines = maxLines,
        textAlign = textAlign
    )
}

@Composable
fun TextHeadlineLarge(text: String, modifier: Modifier = Modifier, color: Color = Black, maxLines: Int = 2, textAlign: TextAlign = TextAlign.Center) {
    Text(
        text = text,
        style = Typography.headlineLarge,
        color = color,
        modifier = modifier,
        maxLines = maxLines,
        textAlign = textAlign
    )
}

@Composable
fun TextHeadlineMedium(text: String, modifier: Modifier = Modifier, color: Color = Black, maxLines: Int = 2, textAlign: TextAlign = TextAlign.Center) {
    Text(
        text = text,
        style = Typography.headlineMedium,
        color = color,
        modifier = modifier,
        maxLines = maxLines,
        textAlign = textAlign
    )
}

@Composable
fun TextHeadlineSmall(text: String, modifier: Modifier = Modifier, color: Color = Black, maxLines: Int = 2, textAlign: TextAlign = TextAlign.Center) {
    Text(
        text = text,
        style = Typography.headlineSmall,
        color = color,
        modifier = modifier,
        maxLines = maxLines,
        textAlign = textAlign
    )
}

@Composable
fun TextHeadlineSmall(text: String, color: Color = Black, maxLines: Int = 2, textAlign: TextAlign = TextAlign.Center, clickEvent: () -> Unit) {
    Text(
        text = text,
        style = Typography.headlineSmall,
        color = color,
        maxLines = maxLines,
        textAlign = textAlign,
        modifier = Modifier.clickable { clickEvent.invoke() }
    )
}

@Composable
fun TextLabelLarge(text: String, color: Color = Black, maxLines: Int = 2, textAlign: TextAlign = TextAlign.Center, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = Typography.labelLarge,
        color = color,
        maxLines = maxLines,
        modifier = modifier,
        textAlign = textAlign,
    )
}

@Composable
fun TextLabelMedium(text: String, color: Color = Black, maxLines: Int = 2, textAlign: TextAlign = TextAlign.Center, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = Typography.labelMedium,
        color = color,
        maxLines = maxLines,
        modifier = modifier,
        textAlign = textAlign,
    )
}

@Composable
fun TextLabelSmall(text: String, color: Color = Black, maxLines: Int = 2, textAlign: TextAlign = TextAlign.Center, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = Typography.labelSmall,
        color = color,
        maxLines = maxLines,
        textAlign = textAlign,
        modifier = modifier,
    )
}

@Composable
fun OutlineTextLabelSmall(
    text: String = "",
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .border(BorderStroke(1.dp, Yellow), roundShapeCorner())
        .padding(horizontal = 10.dp, vertical = 5.dp)
) {
    Text(
        text,
        textAlign = textAlign,
        modifier = modifier,
        style = Typography.labelSmall,
        color = Black,
    )
}