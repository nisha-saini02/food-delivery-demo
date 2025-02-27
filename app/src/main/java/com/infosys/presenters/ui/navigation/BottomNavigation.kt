package com.infosys.presenters.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.infosys.R
import com.infosys.presenters.viewmodel.MainViewModel

@Composable
fun BottomNavigationController(viewModel: MainViewModel) {
    val navHostController = rememberNavController()
    Scaffold (
        bottomBar = {
            BottomBar(navHostController)
        }
    ) { paddingValues ->
        BottomNavHost(navHostController, viewModel, paddingValues)
    }
}

@Composable
fun BottomBar(navHostController: NavHostController) {
    val index = remember { mutableStateOf(0) }

    NavigationBar {
        navigationItems.forEachIndexed { i, navigationItem ->
            if (i < 4) {
                NavigationBarItem(
                    selected = index.value == i,
                    onClick = {
//                    index.intValue = i
//                    navHostController.navigate(navigationItem.name)
                        if (index.value != i) { // Only navigate if the index is different
                            index.value = i
                            navHostController.navigate(navigationItem.name) {
                                // Prevent going back to the same screen if already in it
                                popUpTo(navHostController.graph.startDestinationId) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        }
                    },
                    icon = {
                        BadgedBox(badge = {}) {
                            Icon(
                                painterResource(
                                    if (index.value == i) {
                                        navigationItem.focusedIcon
                                    } else {
                                        navigationItem.unfocusedIcon
                                    }
                                ), null
                            )
                        }
                    },
                    alwaysShowLabel = false
                )
            }
        }
    }
}

val navigationItems = listOf(
    NavigationItem("home", R.drawable.home_selected, R.drawable.home_unselected),
    NavigationItem("menu", R.drawable.menu_selected, R.drawable.menu_unselected),
    NavigationItem("cart", R.drawable.cart_selected, R.drawable.cart_unselected),
    NavigationItem("profile", R.drawable.person_selected, R.drawable.person_unselected),
    NavigationItem("subcategory", R.drawable.person_selected, R.drawable.person_unselected),
)

data class NavigationItem(
    val name: String,
    @DrawableRes
    val focusedIcon: Int,
    @DrawableRes
    val unfocusedIcon: Int
)