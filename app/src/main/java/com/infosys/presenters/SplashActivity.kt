package com.infosys.presenters

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.infosys.presenters.ui.theme.FoodDeliveryDemoTheme
import com.infosys.presenters.ui.SplashScreen

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodDeliveryDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    SplashScreen(this)
                }
            }
        }
    }
}