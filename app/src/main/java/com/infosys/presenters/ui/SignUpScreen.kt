package com.infosys.presenters.ui

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.infosys.presenters.OtpActivity
import com.infosys.presenters.ui.theme.Gray
import com.infosys.presenters.ui.theme.Orange
import com.infosys.presenters.ui.theme.White
import com.infosys.presenters.ui.theme.Yellow

@Composable
fun SignUpScreen(
    context: Activity
) {
    val termsAccepted = remember { mutableStateOf(false) }
    val email = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {

        Box (
            modifier = Modifier
                .fillMaxWidth()
                .background(Orange, roundShapeCorner(0,0,30,30))
                .weight(0.5f),
            contentAlignment = Alignment.Center
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                TextHeadlineLarge("It's all starts with us", color = White)
                Spacer()
                TextHeadlineLarge("Sign Up", color = White)
            }
        }

        Box (
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                TextHeadlineSmall("Email")
                Spacer(5)
                OutlineTextBodyMedium (email.value, keyboardType = KeyboardType.Email) {
                    email.value = it
                }

                Spacer()

                TextHeadlineSmall("Name")
                Spacer(5)
                OutlineTextBodyMedium (name.value) {
                    name.value = it
                }

                Spacer()

                TextHeadlineSmall("Password")
                Spacer(5)
                OutlineTextBodyMedium(
                    password.value,
                    visualTransformation = PasswordVisualTransformation()) {
                    password.value = it
                }

                Spacer(24)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Switch(termsAccepted.value) {
                        termsAccepted.value = it
                    }
                    Spacer()
                    TextTitleSmall("I agree to the Terms and Conditions")
                }
            }
        }

        Box (
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
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
                    text = "Sign up"
                ) {
                    OtpActivity.startActivity(context)
                }

                Spacer()

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextHeadlineSmall("Already have an account?", color = Gray)
                    Spacer(6)
                    TextHeadlineSmall("Sign In", color = Gray) {
                        //handle click event
                    }
                }

                Spacer(30)
            }
        }
    }
}