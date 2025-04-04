package com.infosys.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.google.firebase.FirebaseApp
import com.infosys.data.firebase.FirebaseAuthentication
import com.infosys.data.firebase.FirebaseFirestore
import com.infosys.presentation.ui.screens.navigation.BottomNavigationController
import com.infosys.presentation.viewmodel.CardInfoViewModel
import com.infosys.presentation.viewmodel.UserViewModel
import com.infosys.presentation.viewmodel.HomeViewModel
import com.infosys.presentation.viewmodel.LocalCartViewModel
import com.infosys.presentation.viewmodel.LocalMenuCartViewModel
import com.infosys.presentation.viewmodel.MenuViewModel
import com.infosys.theme.FoodDeliveryDemoTheme
import com.infosys.presentation.viewmodel.OrdersLocalViewModel
import com.infosys.presentation.viewmodel.SignupUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val cardInfoViewModel: CardInfoViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    private val localCartViewModel: LocalCartViewModel by viewModels()
    private val localMenuCartViewModel: LocalMenuCartViewModel by viewModels()
    private val menuViewModel: MenuViewModel by viewModels()
    private val ordersLocalViewModel: OrdersLocalViewModel by viewModels()
    private val signupUserViewModel: SignupUserViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var objFirebase: FirebaseAuthentication
    private lateinit var objFirebaseFirestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            FirebaseApp.initializeApp(this)
            objFirebase = FirebaseAuthentication(this)
            objFirebaseFirestore = FirebaseFirestore

            userViewModel.readUserInfo()
            FoodDeliveryDemoTheme {
                BottomNavigationController(
                    cardInfoViewModel,
                    homeViewModel,
                    localCartViewModel,
                    localMenuCartViewModel,
                    menuViewModel,
                    ordersLocalViewModel,
                    signupUserViewModel,
                    userViewModel,
                    objFirebase,
                    objFirebaseFirestore
                )
            }
        }
    }
}