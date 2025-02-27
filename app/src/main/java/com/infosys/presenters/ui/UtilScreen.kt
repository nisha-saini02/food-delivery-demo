package com.infosys.presenters.ui

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.infosys.R
import com.infosys.presenters.ui.theme.Black
import com.infosys.presenters.ui.theme.Gray
import com.infosys.presenters.ui.theme.Transparent
import com.infosys.presenters.ui.theme.Typography
import com.infosys.presenters.ui.theme.White
import com.infosys.presenters.ui.theme.Yellow

/***************************************** todo: Texts ************************************************/
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
fun TextLabelLarge(text: String, color: Color = Black, maxLines: Int = 2, textAlign: TextAlign = TextAlign.Center) {
    Text(
        text = text,
        style = Typography.labelLarge,
        color = color,
        maxLines = maxLines,
        textAlign = textAlign,
    )
}

@Composable
fun TextLabelSmall(text: String, color: Color = Black, maxLines: Int = 2, textAlign: TextAlign = TextAlign.Center) {
    Text(
        text = text,
        style = Typography.labelSmall,
        color = color,
        maxLines = maxLines,
        textAlign = textAlign,
    )
}

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
fun EditTextBodyMedium(
    text: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier
        .fillMaxWidth(),
    hint: String = "",
    onValueChange: (String) -> Unit
) {
    TextField(
        text,
        onValueChange = {
            onValueChange.invoke(it)
        },
        textStyle = Typography.bodyMedium.copy(textAlign = textAlign),
        modifier = modifier,
        visualTransformation = visualTransformation,
        singleLine = singleLine,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = White,
            unfocusedTextColor = White,
            focusedBorderColor = Transparent,
            unfocusedBorderColor = Transparent,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType
        ),
        placeholder = {
            TextHeadlineSmall(hint, color = White)
        }
    )
}


/***************************************** todo: Button ************************************************/
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
            style = Typography.titleLarge,
            color = textColor,
            text = text,
            modifier = modifier
        )
    }
}



/***************************************** todo: Image ************************************************/
@Composable
fun Image(@DrawableRes id: Int, modifier: Modifier = Modifier.fillMaxWidth()) {
    androidx.compose.foundation.Image(
        painter = painterResource(id),
        contentDescription = "Logo",
        modifier = modifier
    )
}

@Composable
fun Image(@DrawableRes id: Int, modifier: Modifier = Modifier.fillMaxWidth(), clickEvent: () -> Unit) {
    androidx.compose.foundation.Image(
        painter = painterResource(id),
        contentDescription = "Logo",
        modifier = modifier.clickable { clickEvent.invoke() }
    )
}

@Composable
fun LoadImage(url: String,
              modifier: Modifier = Modifier
                  .width(50.dp)
                  .height(50.dp)
){
    AsyncImage(
        model = url,
        contentDescription = "Image Description",
        modifier = modifier.clip(roundShapeCorner(5)),
        contentScale = ContentScale.Crop,
        placeholder = painterResource(R.drawable.placeholder),
        error = painterResource(R.drawable.no_image_found),
        onError = { exception -> Log.e("AsyncImage", "Error loading image: ${exception.result}") }
    )
}

@Composable
fun LoadImage(url: String,
              modifier: Modifier = Modifier
                  .width(50.dp)
                  .height(50.dp),
              clickEvent: () -> Unit
){
    AsyncImage(
        model = url,
        contentDescription = "Image Description",
        modifier = modifier.clip(roundShapeCorner(5)).clickable { clickEvent.invoke() },
        contentScale = ContentScale.Crop,
        placeholder = painterResource(R.drawable.placeholder),
        error = painterResource(R.drawable.no_image_found),
        onError = { exception -> Log.e("AsyncImage", "Error loading image: ${exception.result}") }
    )
}


/***************************************** todo: Spacer ************************************************/
@Composable
fun Spacer(size: Int = 16) =
    androidx.compose.foundation.layout.Spacer(modifier = Modifier.size(size.dp))



/***************************************** todo: Shape ************************************************/
@Composable
fun roundShapeCorner(shape: Int = 12) = RoundedCornerShape(shape.dp)

@Composable
fun roundShapeCorner(tS: Int, tE: Int, bS: Int, bE: Int) =
    RoundedCornerShape(topStart = tS.dp, topEnd = tE.dp, bottomStart = bS.dp, bottomEnd = bE.dp)


/***************************************** todo: Switch ************************************************/
@Composable
fun Switch(flag: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Switch(
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