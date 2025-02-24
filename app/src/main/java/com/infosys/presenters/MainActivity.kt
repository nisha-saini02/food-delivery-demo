package com.infosys.presenters

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.infosys.presenters.viewmodel.MainViewModel
import com.infosys.presenters.ui.MainScreen
import com.infosys.presenters.ui.theme.FoodDeliveryDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodDeliveryDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    viewModel.getAllCategories()
                    MainScreen(viewModel)
                }
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
