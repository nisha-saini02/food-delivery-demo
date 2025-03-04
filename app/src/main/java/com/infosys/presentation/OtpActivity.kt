package com.infosys.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.infosys.presentation.ui.screens.onboarding.OtpScreen
import com.infosys.theme.FoodDeliveryDemoTheme

class OtpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodDeliveryDemoTheme {
                OtpScreen(this)
            }
        }
    }

    companion object {
        fun startActivity(activity: Activity) {
            Intent(activity, OtpActivity::class.java).apply {
                activity.startActivity(this)
                activity.finishAffinity()
            }
        }
    }
}