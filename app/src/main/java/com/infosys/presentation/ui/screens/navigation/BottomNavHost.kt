package com.infosys.presentation.ui.screens.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.infosys.presentation.ui.screens.main.CartScreen
import com.infosys.presentation.ui.screens.main.CheckoutScreen
import com.infosys.presentation.ui.screens.main.MainMenuScreen
import com.infosys.presentation.ui.screens.main.MainScreen
import com.infosys.presentation.ui.screens.main.ProfileScreen
import com.infosys.presentation.ui.screens.main.SearchAddressScreen
import com.infosys.presentation.ui.screens.main.SubCategoryScreen
import com.infosys.presentation.ui.screens.map.OrderPlaceScreen
import com.infosys.presentation.viewmodel.LocalViewModel
import com.infosys.presentation.viewmodel.MainViewModel

@Composable
fun BottomNavHost(
    navHostController: NavHostController,
    viewModel: MainViewModel,
    localViewModel: LocalViewModel,
    snackBarHost: SnackbarHostState
) {
    NavHost(navController = navHostController, startDestination = NavigationRoute.HOME.route) {
        composable(NavigationRoute.HOME.route) {
            viewModel.getAllCategories()
            MainScreen(viewModel)
        }
        composable(NavigationRoute.MENU.route) {
            viewModel.getMenuList()
            MainMenuScreen(viewModel, localViewModel, navHostController)
        }
        composable(NavigationRoute.CART.route) {
            localViewModel.getAllCartItems()
            CartScreen(localViewModel, navHostController)
        }
        composable(NavigationRoute.PROFILE.route) {
            ProfileScreen()
        }
        composable(NavigationRoute.SUBCATEGORY.route) {
            SubCategoryScreen(viewModel, localViewModel)
        }
        composable(NavigationRoute.CHECKOUT.route) {
            CheckoutScreen(navHostController, snackBarHost)
        }
        composable(NavigationRoute.ADDRESS.route) {
            SearchAddressScreen(navHostController)
        }
        composable(NavigationRoute.ORDER.route) {
            OrderPlaceScreen()
        }
    }
}