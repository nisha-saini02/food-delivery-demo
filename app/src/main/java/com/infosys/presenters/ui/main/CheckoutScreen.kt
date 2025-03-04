package com.infosys.presenters.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.infosys.data.model.card.getCardType
import com.infosys.data.model.enums.CheckoutValidation
import com.infosys.presenters.ui.ButtonCr
import com.infosys.presenters.ui.OutlineTextBodySmall
import com.infosys.presenters.ui.Spacer
import com.infosys.presenters.ui.TextLabelLarge
import com.infosys.presenters.ui.TextLabelMedium
import com.infosys.presenters.ui.TextLabelSmall
import com.infosys.presenters.ui.TextTitleMedium
import com.infosys.presenters.ui.navigation.navigationItems
import com.infosys.presenters.ui.roundShapeCorner
import com.infosys.presenters.ui.theme.Black
import com.infosys.presenters.ui.theme.Blue
import com.infosys.presenters.ui.theme.Cream
import com.infosys.presenters.ui.theme.Orange
import com.infosys.presenters.ui.theme.White
import com.infosys.utils.validations.checkout
import kotlinx.coroutines.launch

@Composable
fun CheckoutScreen(navHostController: NavHostController, snackBarHost: SnackbarHostState) {

    val coroutineState = rememberCoroutineScope()

    val name = remember { mutableStateOf("") }
    val account = remember { mutableStateOf("") }
    val cvv = remember { mutableStateOf("") }
    val year = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Orange, roundShapeCorner(0, 0, 30, 30))
                .weight(0.15f),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            ) {
                TextTitleMedium("Checkout", color = White)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 110.dp, start = 16.dp, end = 16.dp, top = 16.dp)
                .weight(0.85f),
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Orange, Cream, Orange),
                                start = Offset(0f, 0f),
                                end = Offset(1000f, 1000f)
                            ), roundShapeCorner()
                        )
                        .padding(16.dp)
                ) {
                    Row {
                        Box(Modifier.weight(0.7f)) {
                            TextLabelMedium(name.value)
                        }
                        Box(
                            Modifier
                                .weight(0.3f)
                                .background(White, roundShapeCorner(8)).padding(5.dp),
                            Alignment.Center
                        ) {
                            TextLabelMedium(
                                getCardType(account.value) ?: "error",
                                Blue, maxLines = Int.MAX_VALUE
                            )
                        }
                    }
                    Spacer()
                    TextLabelLarge(formatAccountNumber(account.value))
                    TextLabelSmall("Account number")
                    Spacer()
                    Row {
                        Box(modifier = Modifier.weight(0.5f)) {
                            TextLabelMedium(cvv.value)
                        }
                        Box(modifier = Modifier.weight(0.5f)) {
                            TextLabelMedium(year.value)
                        }
                    }
                    Row {
                        Box(modifier = Modifier.weight(0.5f)) {
                            TextLabelSmall("cvv number")
                        }
                        Box(modifier = Modifier.weight(0.5f)) {
                            TextLabelSmall("Expiry date")
                        }
                    }
                }

                Spacer()

                OutlineTextBodySmall(
                    name.value,
                    hint = "Enter name",
                    label = "Account holder name",
                    textColor = Black,
                    hintColor = Black,
                    borderColor = Color.Gray,
                    maxLength = 32
                ) {
                    name.value = it
                }

                Spacer(5)

                OutlineTextBodySmall(
                    account.value,
                    hint = "xxxx xxxx xxxx xxxx",
                    label = "Account number",
                    textColor = Black,
                    hintColor = Black,
                    borderColor = Color.Gray,
                    keyboardType = KeyboardType.Number,
                    maxLength = 19
                ) {
                    account.value = it
                }

                Spacer(5)

                Row {
                    Box(Modifier.weight(1f)) {
                        OutlineTextBodySmall(
                            year.value.split('/').first(),
                            hint = "MM",
                            label = "Expiry Month",
                            textColor = Black,
                            hintColor = Black,
                            borderColor = Color.Gray,
                            keyboardType = KeyboardType.Number,
                            maxLength = 2
                        ) {
                            year.value = it + "/" + year.value.split('/').last()
                        }
                    }

                    Spacer(5)

                    Box(Modifier.weight(1f)) {
                        OutlineTextBodySmall(
                            year.value.split('/').last(),
                            hint = "YY",
                            label = "Expiry Year",
                            textColor = Black,
                            hintColor = Black,
                            borderColor = Color.Gray,
                            keyboardType = KeyboardType.Number,
                            maxLength = 2
                        ) {
                            year.value = year.value.split('/').first() + "/" + it
                        }
                    }
                }

                Spacer(5)

                OutlineTextBodySmall(
                    cvv.value,
                    hint = "cvv",
                    label = "cvv",
                    textColor = Black,
                    hintColor = Black,
                    borderColor = Color.Gray,
                    keyboardType = KeyboardType.Number,
                    maxLength = 3
                ) {
                    cvv.value = it
                }

                Spacer()

                ButtonCr(
                    text = "Select Address",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    when(checkout(name.value, account.value, year.value.split('/').first(), year.value.split('/').last(), cvv.value)) {
                        CheckoutValidation.ENTER_VALID_ACCOUNT_NUMBER -> {
                            coroutineState.launch {
                                snackBarHost.showSnackbar(
                                    "Enter valid account number",
                                    null,
                                    true,
                                    SnackbarDuration.Short
                                )
                            }
                        }
                        CheckoutValidation.ENTER_VALID_MONTH -> {
                            coroutineState.launch {
                                snackBarHost.showSnackbar(
                                    "Enter valid month",
                                    null,
                                    true,
                                    SnackbarDuration.Short
                                )
                            }
                        }
                        CheckoutValidation.ENTER_VALID_YEAR -> {
                            coroutineState.launch {
                                snackBarHost.showSnackbar(
                                    "Enter valid year",
                                    null,
                                    true,
                                    SnackbarDuration.Short
                                )
                            }
                        }
                        CheckoutValidation.INVALID_CVV -> {
                            coroutineState.launch {
                                snackBarHost.showSnackbar(
                                    "Enter valid cvv",
                                    null,
                                    true,
                                    SnackbarDuration.Short
                                )
                            }
                        }
                        CheckoutValidation.FIELDS_ARE_EMPTY -> {
                            coroutineState.launch {
                                snackBarHost.showSnackbar(
                                    "Enter all fields",
                                    null,
                                    true,
                                    SnackbarDuration.Short
                                )
                            }
                        }
                        CheckoutValidation.VALIDATE -> {
                            navHostController.navigate(navigationItems[6].name)
                        }
                    }
                }
            }
        }
    }
}

private fun formatAccountNumber(input: String): String {
    // Remove all non-digit characters to keep only numbers
    val digits = input.filter { it.isDigit() }

    // Format the string to "xxxx xxxx xxxx xxxx" format
    val formatted = StringBuilder()
    var count = 0

    for (digit in digits) {
        if (count == 4) {
            formatted.append(" ")
            count = 0
        }
        formatted.append(digit)
        count++
    }

    return formatted.toString()
}