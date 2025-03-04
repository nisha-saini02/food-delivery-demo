package com.infosys.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.infosys.presentation.ui.screens.onboarding.SignUpScreen
import com.infosys.theme.FoodDeliveryDemoTheme

class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodDeliveryDemoTheme {
                SignUpScreen(this)
            }
        }
    }

    companion object {
        fun startActivity(activity: Activity) {
            Intent(activity, SignupActivity::class.java).apply {
                activity.startActivity(this)
                activity.finishAffinity()
            }
        }
    }
}