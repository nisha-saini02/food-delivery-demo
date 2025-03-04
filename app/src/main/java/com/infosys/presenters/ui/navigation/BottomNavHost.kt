package com.infosys.presenters.ui.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.infosys.presenters.ui.main.CartScreen
import com.infosys.presenters.ui.main.CheckoutScreen
import com.infosys.presenters.ui.main.MainMenuScreen
import com.infosys.presenters.ui.main.MainScreen
import com.infosys.presenters.ui.main.ProfileScreen
import com.infosys.presenters.ui.main.SearchAddressScreen
import com.infosys.presenters.ui.main.SubCategoryScreen
import com.infosys.presenters.ui.map.OrderPlaceScreen
import com.infosys.presenters.viewmodel.LocalViewModel
import com.infosys.presenters.viewmodel.MainViewModel

@Composable
fun BottomNavHost(navHostController: NavHostController, viewModel: MainViewModel, localViewModel: LocalViewModel, snackBarHost: SnackbarHostState) {
    NavHost(navController = navHostController, startDestination = navigationItems[0].name) {
        composable(navigationItems[0].name) {
            viewModel.getAllCategories()
            MainScreen(viewModel)
        }
        composable(navigationItems[1].name) {
            viewModel.getMenuList()
            MainMenuScreen(viewModel, localViewModel, navHostController)
        }
        composable(navigationItems[2].name) {
            localViewModel.getAllCartItems()
            CartScreen(localViewModel, navHostController)
        }
        composable(navigationItems[3].name) {
            ProfileScreen()
        }
        composable(navigationItems[4].name) {
            SubCategoryScreen(viewModel, localViewModel)
        }
        composable(navigationItems[5].name) {
            CheckoutScreen(navHostController, snackBarHost)
        }
        composable(navigationItems[6].name) {
            SearchAddressScreen(navHostController)
        }
        composable(navigationItems[7].name) {
            OrderPlaceScreen()
        }
    }
}