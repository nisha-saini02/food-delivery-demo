package com.infosys.presentation.ui.screens.navigation

import android.util.Log
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.infosys.R
import com.infosys.data.firebase.FirebaseAuthentication
import com.infosys.data.firebase.FirebaseFirestore
import com.infosys.presentation.viewmodel.CardInfoViewModel
import com.infosys.presentation.viewmodel.HomeViewModel
import com.infosys.presentation.viewmodel.LocalCartViewModel
import com.infosys.presentation.viewmodel.LocalMenuCartViewModel
import com.infosys.presentation.viewmodel.MenuViewModel
import com.infosys.presentation.viewmodel.OrdersLocalViewModel
import com.infosys.presentation.viewmodel.SignupUserViewModel
import com.infosys.presentation.viewmodel.UserViewModel
import com.infosys.theme.Gray
import com.infosys.theme.Orange

@Composable
fun BottomNavigationController(
    cardInfoViewModel: CardInfoViewModel,
    homeViewModel: HomeViewModel,
    localCartViewModel: LocalCartViewModel,
    localMenuCartViewModel: LocalMenuCartViewModel,
    menuViewModel: MenuViewModel,
    ordersLocalViewModel: OrdersLocalViewModel,
    signupUserViewModel: SignupUserViewModel,
    userViewModel: UserViewModel,
    objFirebase: FirebaseAuthentication,
    objFirebaseFirestore: FirebaseFirestore
) {
    val navHostController = rememberNavController()
    val snackBarHost = remember { SnackbarHostState() }

    Scaffold (
        bottomBar = {
            val currentRoute = currentRoute(navHostController)
            if (
                currentRoute != null &&
                currentRoute != NavigationRoute.SPLASH.route &&
                currentRoute != NavigationRoute.SIGNUP.route &&
                currentRoute != NavigationRoute.SIGN_IN.route &&
                currentRoute != NavigationRoute.OTP.route
                ) {
                BottomBar(navHostController)
            }
        },
        snackbarHost = { SnackbarHost(snackBarHost) }
    ) { _ ->
        BottomNavHost(
            navHostController,
            cardInfoViewModel,
            homeViewModel,
            localCartViewModel,
            localMenuCartViewModel,
            menuViewModel,
            ordersLocalViewModel,
            signupUserViewModel,
            userViewModel,
            snackBarHost,
            objFirebase,
            objFirebaseFirestore
        )
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val route = navBackStackEntry?.destination?.route
    Log.e("TAG", "currentRoute: $route")
    return route
}

@Composable
fun BottomBar(navHostController: NavHostController) {
    val index = remember { mutableStateOf(0) }

    NavigationBar {
        listOf(
            NavigationBarItem(
                selected = index.value == 0,
                onClick = {
                    navHostController.navigate(NavigationRoute.HOME.route) {
                        popUpTo(navHostController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                    index.value = 0
                },
                icon = {
                    BadgedBox(badge = {}) {
                        Icon(
                            painterResource(
                                if (index.value == 0) R.drawable.home_selected
                                else R.drawable.home_unselected
                            ), null,
                            tint = if (index.value == 0) Orange else Gray,
                        )
                    }
                },
                alwaysShowLabel = false,
            ),
            NavigationBarItem(
                selected = index.value == 1,
                onClick = {
                    navHostController.navigate(NavigationRoute.MENU.route)
                    index.value = 1
                },
                icon = {
                    BadgedBox(badge = {}) {
                        Icon(
                            painterResource(
                                if (index.value == 1) R.drawable.menu_selected
                                else R.drawable.menu_unselected
                            ), null,
                            tint = if (index.value == 1) Orange else Gray,
                        )
                    }
                },
                alwaysShowLabel = false,
            ),
            NavigationBarItem(
                selected = index.value == 2,
                onClick = {
                    navHostController.navigate(NavigationRoute.CART.route)
                    index.value = 2
                },
                icon = {
                    BadgedBox(badge = {}) {
                        Icon(
                            painterResource(
                                if (index.value == 2) R.drawable.cart_selected
                                else R.drawable.cart_unselected
                            ), null,
                            tint = if (index.value == 2) Orange else Gray,
                        )
                    }
                },
                alwaysShowLabel = false,
            ),
            NavigationBarItem(
                selected = index.value == 3,
                onClick = {
                    navHostController.navigate(NavigationRoute.ORDER.route)
                    index.value = 3
                },
                icon = {
                    BadgedBox(badge = {}) {
                        Icon(
                            painterResource(
                                R.drawable.ic_orders
                            ), null,
                            tint = if (index.value == 3) Orange else Gray,
                        )
                    }
                },
                alwaysShowLabel = false,
            ),
            NavigationBarItem(
                selected = index.value == 4,
                onClick = {
                    navHostController.navigate(NavigationRoute.PROFILE.route)
                    index.value = 4
                },
                icon = {
                    BadgedBox(badge = {}) {
                        Icon(
                            painterResource(
                                if (index.value == 4) R.drawable.person_selected
                                else R.drawable.person_unselected
                            ), null,
                            tint = if (index.value == 4) Orange else Gray,
                        )
                    }
                },
                alwaysShowLabel = false,
            )
        )
    }
}