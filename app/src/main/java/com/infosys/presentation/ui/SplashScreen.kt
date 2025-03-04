package com.infosys.presentation.ui

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.infosys.R
import com.infosys.presentation.SignupActivity
import com.infosys.presentation.theme.Yellow

@Composable
fun SplashScreen(
    context: Activity
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 60.dp, horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier.weight(1.2f)
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TextHeadlineLarge("DELIVERY FOOD")

                Image(R.drawable.logo, Modifier.fillMaxWidth().weight(1f))

            }
        }

        Box(
            modifier = Modifier.weight(0.8f)
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                TextTitleMedium("Your journey starts \nhere")

                Spacer()

                ButtonCr(
                    bgColor = Yellow,
                    text = "GET STARTED",
                    clickEvent = { SignupActivity.startActivity(context) })

                Spacer()

                TextTitleLarge("Take me there") {
                    MainActivity.startActivity(context)
                }
            }
        }
    }
}