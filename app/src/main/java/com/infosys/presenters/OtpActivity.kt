package com.infosys.presenters

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.infosys.presenters.ui.OtpScreen
import com.infosys.presenters.ui.theme.FoodDeliveryDemoTheme

class OtpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodDeliveryDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    OtpScreen(this)
                }
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