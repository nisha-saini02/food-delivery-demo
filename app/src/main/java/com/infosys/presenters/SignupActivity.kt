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
import com.infosys.presenters.ui.SignUpScreen
import com.infosys.presenters.ui.theme.FoodDeliveryDemoTheme

class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodDeliveryDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    SignUpScreen(this)
                }
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