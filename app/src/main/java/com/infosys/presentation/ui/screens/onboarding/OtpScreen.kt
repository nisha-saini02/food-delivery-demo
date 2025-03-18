package com.infosys.presentation.ui.screens.onboarding

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.infosys.presentation.ui.screens.utility.ButtonCr
import com.infosys.presentation.ui.screens.utility.OutlineTextBodyMedium
import com.infosys.presentation.ui.screens.utility.Spacer
import com.infosys.presentation.ui.screens.utility.TextHeadlineLarge
import com.infosys.presentation.ui.screens.utility.TextTitleSmall
import com.infosys.presentation.ui.screens.navigation.NavigationRoute
import com.infosys.presentation.ui.screens.utility.roundShapeCorner
import com.infosys.presentation.viewmodel.SignupUserViewModel
import com.infosys.presentation.viewmodel.UserViewModel
import com.infosys.theme.Orange
import com.infosys.theme.White
import com.infosys.theme.Yellow
import kotlinx.coroutines.launch

@Composable
fun OtpScreen(
    navHostController: NavHostController,
    authViewModel: UserViewModel,
    signupUserViewModel: SignupUserViewModel
) {
    val otp1 = remember { mutableStateOf("") }
    val otp2 = remember { mutableStateOf("") }
    val otp3 = remember { mutableStateOf("") }

    val snackBarHost = remember { SnackbarHostState() }
    val coroutineState = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackBarHost) }
    ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Orange, roundShapeCorner(0, 0, 30, 30))
                    .weight(0.15f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    TextHeadlineLarge("OTP Verification", color = White)
                }
            }

            Spacer()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.85f),
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {

                        OutlineTextBodyMedium(
                            otp1.value,
                            modifier = Modifier
                                .width(60.dp)
                                .height(60.dp)
                                .border(BorderStroke(2.dp, Yellow), roundShapeCorner(10)),
                            textAlign = TextAlign.Center,
                            keyboardType = KeyboardType.Number
                        ) {
                            if (it.length == 1) {
                                otp1.value = it
                            }
                        }

                        Spacer()

                        OutlineTextBodyMedium(
                            otp2.value,
                            modifier = Modifier
                                .width(60.dp)
                                .height(60.dp)
                                .border(BorderStroke(2.dp, Yellow), roundShapeCorner(10)),
                            textAlign = TextAlign.Center,
                            keyboardType = KeyboardType.Number
                        ) {
                            if (it.length == 1) {
                                otp2.value = it
                            }
                        }

                        Spacer()

                        OutlineTextBodyMedium(
                            otp3.value,
                            modifier = Modifier
                                .width(60.dp)
                                .height(60.dp)
                                .border(BorderStroke(2.dp, Yellow), roundShapeCorner(10)),
                            textAlign = TextAlign.Center,
                            keyboardType = KeyboardType.Number
                        ) {
                            if (it.length == 1) {
                                otp3.value = it
                            }
                        }
                    }

                    Spacer(24)

                    ButtonCr(text = "Verify") {
                        if (otp1.value.isNotEmpty() && otp2.value.isNotEmpty() && otp3.value.isNotEmpty()) {
                            authViewModel.readUserInfo()

                            val user = authViewModel.userInfo.value
                            user?.authenticate = true
                            user?.let { signupUserViewModel.writeUserInfo(it) }

                            navHostController.navigate(NavigationRoute.HOME.route) {
                                popUpTo(NavigationRoute.OTP.route){
                                    inclusive = true
                                }
                                popUpTo(NavigationRoute.SIGNUP.route){
                                    inclusive = true
                                }
                                popUpTo(NavigationRoute.SPLASH.route){
                                    inclusive = true
                                }
                            }
                        } else {
                            coroutineState.launch {
                                snackBarHost.showSnackbar(
                                    "Please enter otp",
                                    null,
                                    true,
                                    SnackbarDuration.Short
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}