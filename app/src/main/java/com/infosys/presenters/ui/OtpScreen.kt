package com.infosys.presenters.ui

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.infosys.presenters.MainActivity
import com.infosys.presenters.ui.theme.Orange
import com.infosys.presenters.ui.theme.White
import com.infosys.presenters.ui.theme.Yellow

@Composable
fun OtpScreen(
    context: Activity
) {
    val otp1 = remember { mutableStateOf("") }
    val otp2 = remember { mutableStateOf("") }
    val otp3 = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Box (
            modifier = Modifier
                .fillMaxWidth()
                .background(Orange, roundShapeCorner(0,0,30,30))
                .weight(0.2f),
            contentAlignment = Alignment.Center
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                TextHeadlineLarge("OTP Verification", color = White)
            }
        }

        Spacer()

        Box (
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TextTitleSmall("Enter the 3-digit OTP sent to your phone")

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
                ) {

                    EditTextBodyMedium (
                        otp1.value,
                        modifier = Modifier
                            .width(60.dp)
                            .height(60.dp)
                            .border(BorderStroke(2.dp, Yellow), roundShapeCorner(10)),
                        textAlign = TextAlign.Center) {
                        if (it.length == 1) {
                            otp1.value = it
                        }
                    }

                    Spacer()
                    
                    EditTextBodyMedium (
                        otp2.value,
                        modifier = Modifier
                            .width(60.dp)
                            .height(60.dp)
                            .border(BorderStroke(2.dp, Yellow), roundShapeCorner(10)),
                        textAlign = TextAlign.Center) {
                        if (it.length == 1) {
                            otp2.value = it
                        }
                    }

                    Spacer()
                    
                    EditTextBodyMedium (
                        otp3.value,
                        modifier = Modifier
                            .width(60.dp)
                            .height(60.dp)
                            .border(BorderStroke(2.dp, Yellow), roundShapeCorner(10)),
                        textAlign = TextAlign.Center) {
                        if (it.length == 1) {
                            otp3.value = it
                        }
                    }
                }

                Spacer(24)

                ButtonCr(text = "Verify") {
                    MainActivity.startActivity(context)
                }
            }
        }
    }
}