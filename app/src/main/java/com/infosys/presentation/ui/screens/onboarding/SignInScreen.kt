package com.infosys.presentation.ui.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.infosys.data.firebase.FirebaseAuthentication
import com.infosys.data.firebase.FirebaseFirestore
import com.infosys.data.model.user.User
import com.infosys.presentation.ui.screens.navigation.NavigationRoute
import com.infosys.presentation.ui.screens.utility.ButtonCr
import com.infosys.presentation.ui.screens.utility.OutlineTextBodyMedium
import com.infosys.presentation.ui.screens.utility.Spacer
import com.infosys.presentation.ui.screens.utility.TextHeadlineLarge
import com.infosys.presentation.ui.screens.utility.TextHeadlineSmall
import com.infosys.presentation.ui.screens.utility.roundShapeCorner
import com.infosys.presentation.viewmodel.SignupUserViewModel
import com.infosys.theme.Gray
import com.infosys.theme.Orange
import com.infosys.theme.White
import com.infosys.theme.Yellow
import com.infosys.utils.enums.LoginType
import com.infosys.utils.enums.SignInValidation
import com.infosys.utils.validations.signIn
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    navHostController: NavHostController,
    authViewModel: SignupUserViewModel,
    objFirebase: FirebaseAuthentication,
    objFirebaseFirestore: FirebaseFirestore,
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val snackBarHost = remember { SnackbarHostState() }
    val coroutineState = rememberCoroutineScope()

    val userInfo = objFirebaseFirestore.userInfo.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackBarHost) }
    ) { _ ->
        Column(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Orange, roundShapeCorner(0, 0, 30, 30))
                    .weight(0.4f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    TextHeadlineLarge("Let's take you inside!", color = White)
                    Spacer()
                    TextHeadlineLarge("Sign In", color = White)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 20.dp)
                ) {
                    TextHeadlineSmall("Email")
                    Spacer(5)
                    OutlineTextBodyMedium(email.value, keyboardType = KeyboardType.Email) {
                        email.value = it
                    }

                    Spacer()

                    TextHeadlineSmall("Password")
                    Spacer(5)
                    OutlineTextBodyMedium(
                        password.value,
                        visualTransformation = PasswordVisualTransformation()
                    ) {
                        password.value = it
                    }

                    Spacer(24)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.6f),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 20.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ButtonCr(
                        Yellow,
                        text = "Sign in"
                    ) {
                        when (signIn(
                            email.value,
                            password.value,
                        )) {
                            SignInValidation.EMAIL_NOT_CORRECT -> {
                                coroutineState.launch {
                                    snackBarHost.showSnackbar(
                                        "Enter valid Email",
                                        null,
                                        true,
                                        SnackbarDuration.Short
                                    )
                                }
                            }

                            SignInValidation.PASSWORD_NOT_CORRECT -> {
                                coroutineState.launch {
                                    snackBarHost.showSnackbar(
                                        "Password must contains 1 lowercase, 1 uppercase, 1 digit, 1 special character & must be between 8 to 20 characters",
                                        null,
                                        true,
                                        SnackbarDuration.Short
                                    )
                                }
                            }

                            SignInValidation.FIELD_IS_EMPTY -> {
                                coroutineState.launch {
                                    snackBarHost.showSnackbar(
                                        "Please fill all the fields",
                                        null,
                                        true,
                                        SnackbarDuration.Short
                                    )
                                }
                            }

                            SignInValidation.VALIDATE -> {

                                objFirebase.loginWithEmailPassword(email.value, password.value) { success, errorMessage ->
                                    if (success) {
                                        objFirebaseFirestore.fetchUserDetailsByEmail(email.value)
                                        userInfo.value?.let {
                                            authViewModel.writeUserInfo(it)
                                            navHostController.navigate(NavigationRoute.HOME.route)
                                        }
                                    }
                                    else {
                                        coroutineState.launch {
                                            snackBarHost.showSnackbar(
                                                errorMessage.toString(),
                                                null,
                                                true,
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Spacer()

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextHeadlineSmall("Don't have an account?", color = Gray)
                        Spacer(6)
                        TextHeadlineSmall("Sign Up", color = Gray) {
                            navHostController.navigate(NavigationRoute.SIGNUP.route)
                        }
                    }

                    Spacer(30)
                }
            }
        }
    }
}