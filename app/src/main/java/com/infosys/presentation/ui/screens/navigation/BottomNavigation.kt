package com.infosys.presentation.ui.screens.navigation

import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.infosys.R
import com.infosys.theme.Gray
import com.infosys.theme.Orange
import com.infosys.presentation.viewmodel.LocalViewModel
import com.infosys.presentation.viewmodel.MainViewModel

@Composable
fun BottomNavigationController(viewModel: MainViewModel, localViewModel: LocalViewModel) {
    val navHostController = rememberNavController()
    val snackBarHost = remember { SnackbarHostState() }

    Scaffold (
        bottomBar = {
            BottomBar(navHostController)
        },
        snackbarHost = { SnackbarHost(snackBarHost) }
    ) { _ ->
        BottomNavHost(navHostController, viewModel, localViewModel, snackBarHost)
    }
}

@Composable
fun BottomBar(navHostController: NavHostController) {
    val index = remember { mutableStateOf(0) }

    NavigationBar {
        listOf(
            NavigationBarItem(
                selected = index.value == 0,
                onClick = {
                    navHostController.navigate(NavigationRoute.HOME.route)
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
                    navHostController.navigate(NavigationRoute.PROFILE.route)
                    index.value = 3
                },
                icon = {
                    BadgedBox(badge = {}) {
                        Icon(
                            painterResource(
                                if (index.value == 3) R.drawable.person_selected
                                else R.drawable.person_unselected
                            ), null,
                            tint = if (index.value == 3) Orange else Gray,
                        )
                    }
                },
                alwaysShowLabel = false,
            )
        )
    }
}