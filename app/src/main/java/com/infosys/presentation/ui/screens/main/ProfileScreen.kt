package com.infosys.presentation.ui.screens.main

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.infosys.R
import com.infosys.data.firebase.FirebaseFirestore
import com.infosys.presentation.ui.screens.navigation.NavigationRoute
import com.infosys.presentation.ui.screens.utility.ButtonCr
import com.infosys.presentation.ui.screens.utility.Image
import com.infosys.presentation.ui.screens.utility.LoadImage
import com.infosys.presentation.ui.screens.utility.OutlineTextLabelSmall
import com.infosys.presentation.ui.screens.utility.Spacer
import com.infosys.presentation.ui.screens.utility.TextHeadlineLarge
import com.infosys.presentation.ui.screens.utility.TextHeadlineSmall
import com.infosys.presentation.ui.screens.utility.roundShapeCorner
import com.infosys.presentation.viewmodel.SignupUserViewModel
import com.infosys.presentation.viewmodel.UserViewModel
import com.infosys.theme.Gray
import com.infosys.theme.Orange
import com.infosys.theme.White
import com.infosys.utils.enums.LoginType
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    authViewModel: UserViewModel,
    navHostController: NavHostController,
    objFirebaseFirestore: FirebaseFirestore,
    signupUserViewModel: SignupUserViewModel,
    snackBarHost: SnackbarHostState
) {
    val userInfo = authViewModel.userInfo.collectAsState().value
    val userImage = remember { mutableStateOf<Uri?>(null) }

    val coroutineState = rememberCoroutineScope()

    val pickMedia = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            userImage.value = uri
            Log.d("PhotoPicker", "Selected URI: $uri")
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
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
                TextHeadlineLarge("Profile", color = White)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.5f)
                .padding(16.dp)
        ) {
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (userImage.value != null) {
                    LoadImage(
                        userImage.value
                    ) {
                        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }
                } else if (userInfo?.imageUri != null) {
                    LoadImage(
                        Uri.parse(userInfo.imageUri)
                    ) {
                        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    }
                } else {
                    Image(
                        R.drawable.person_selected,
                        Modifier
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(White, Gray, Color.Black),
                                    start = Offset(0f, 0f),
                                    end = Offset(1000f, 1000f)
                                ), roundShapeCorner(100)
                            )
                            .padding(16.dp)
                            .size(100.dp)
                    ) {
                        if (userInfo != null && userInfo.type == LoginType.User || userInfo?.authenticate == true) {
                            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        }
                    }
                }

                Spacer()

                if (userInfo == null || userInfo.type == LoginType.Guest || !userInfo.authenticate) {
                    TextHeadlineSmall("Guest User")
                } else {
                    TextHeadlineSmall("Name", Modifier.fillMaxWidth(), textAlign = TextAlign.Start)
                    Spacer(6)
                    OutlineTextLabelSmall("${userInfo.name}")
                    Spacer()
                    TextHeadlineSmall("Email", Modifier.fillMaxWidth(), textAlign = TextAlign.Start)
                    Spacer(6)
                    OutlineTextLabelSmall("${userInfo.email}")
                    Spacer()
                    TextHeadlineSmall("Password", Modifier.fillMaxWidth(), textAlign = TextAlign.Start)
                    Spacer(6)
                    OutlineTextLabelSmall("${userInfo.password}")
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.35f)
                .padding(bottom = 85.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ButtonCr(
                    text =
                        if (userImage.value == null) {
                            if (userInfo == null || userInfo.type == LoginType.Guest || !userInfo.authenticate) {
                                "Login"
                            } else "Logout"
                        } else {
                            "Save"
                        }
                ) {
                    if (userImage.value == null) {
                        if (userInfo != null && userInfo.type == LoginType.User && userInfo.authenticate) {
                            coroutineState.launch {
                                val result = snackBarHost.showSnackbar(
                                    "Are you sure you want to logout?",
                                    "Logout",
                                    true,
                                    SnackbarDuration.Short
                                )

                                when(result) {
                                    SnackbarResult.Dismissed -> {}
                                    SnackbarResult.ActionPerformed -> {
                                        authViewModel.clearUserInfo()
                                        navHostController.navigate(NavigationRoute.SIGNUP.route)
                                    }
                                }
                            }
                        } else {
                            navHostController.navigate(NavigationRoute.SIGN_IN.route)
                        }
                    } else {
                        userInfo?.let {
                            val user = userInfo.apply { it.imageUri = userImage.value.toString() }
                            objFirebaseFirestore.updateUserInfo(user)
                            signupUserViewModel.writeUserInfo(user)
                            userImage.value = null
                        }
                    }
                }
            }
        }
    }
}