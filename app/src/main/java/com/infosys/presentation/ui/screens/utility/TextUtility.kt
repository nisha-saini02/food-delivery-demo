package com.infosys.presentation.ui.screens.utility

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.infosys.theme.Black
import com.infosys.theme.Typography

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