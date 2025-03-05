package com.infosys.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.infosys.presentation.ui.screens.navigation.BottomNavigationController
import com.infosys.presentation.viewmodel.MainViewModel
import com.infosys.theme.FoodDeliveryDemoTheme
import com.infosys.presentation.viewmodel.CartLocalViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val cartLocalViewModel: CartLocalViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodDeliveryDemoTheme {
                BottomNavigationController(viewModel, cartLocalViewModel)
            }
        }
    }
}