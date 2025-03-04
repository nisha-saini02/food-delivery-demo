package com.infosys.presentation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.infosys.presentation.ui.navigation.BottomNavigationController
import com.infosys.presentation.viewmodel.MainViewModel
import com.infosys.presentation.theme.FoodDeliveryDemoTheme
import com.infosys.presentation.viewmodel.LocalViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val localViewModel: LocalViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodDeliveryDemoTheme {
                BottomNavigationController(viewModel, localViewModel)
            }
        }
    }

    companion object {
        fun startActivity(activity: Activity) {
            Intent(activity, MainActivity::class.java).apply {
                activity.startActivity(this)
                activity.finishAffinity()
            }
        }
    }
}