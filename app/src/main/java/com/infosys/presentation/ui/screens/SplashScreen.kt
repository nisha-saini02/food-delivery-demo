package com.infosys.presentation.ui.screens

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
import androidx.navigation.NavHostController
import com.infosys.R
import com.infosys.presentation.ui.screens.navigation.Main
import com.infosys.presentation.ui.screens.navigation.SignUp
import com.infosys.presentation.ui.screens.navigation.Splash
import com.infosys.presentation.ui.screens.utility.ButtonCr
import com.infosys.presentation.ui.screens.utility.Image
import com.infosys.presentation.ui.screens.utility.Spacer
import com.infosys.presentation.ui.screens.utility.TextHeadlineLarge
import com.infosys.presentation.ui.screens.utility.TextTitleLarge
import com.infosys.presentation.ui.screens.utility.TextTitleMedium
import com.infosys.theme.Yellow

@Composable
fun SplashScreen(
    navHostController: NavHostController
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
                    clickEvent = { navHostController.navigate(SignUp) })

                Spacer()

                TextTitleLarge("Take me there") {
                    navHostController.navigate(Main){
                        popUpTo(Splash){
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}