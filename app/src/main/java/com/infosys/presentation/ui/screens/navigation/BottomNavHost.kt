package com.infosys.presentation.ui.screens.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.infosys.presentation.ui.screens.SplashScreen
import com.infosys.presentation.ui.screens.main.CartScreen
import com.infosys.presentation.ui.screens.main.CheckoutScreen
import com.infosys.presentation.ui.screens.main.MainMenuScreen
import com.infosys.presentation.ui.screens.main.MainScreen
import com.infosys.presentation.ui.screens.main.ProfileScreen
import com.infosys.presentation.ui.screens.main.SearchAddressScreen
import com.infosys.presentation.ui.screens.main.SubCategoryScreen
import com.infosys.presentation.ui.screens.map.OrderPlaceScreen
import com.infosys.presentation.ui.screens.onboarding.OtpScreen
import com.infosys.presentation.ui.screens.onboarding.SignUpScreen
import com.infosys.presentation.viewmodel.CartLocalViewModel
import com.infosys.presentation.viewmodel.MainViewModel

@Composable
fun BottomNavHost(
    navHostController: NavHostController,
    viewModel: MainViewModel,
    cartLocalViewModel: CartLocalViewModel,
    snackBarHost: SnackbarHostState
) {
    NavHost(navController = navHostController, startDestination = NavigationRoute.SPLASH.route) {
        composable(NavigationRoute.SPLASH.route) {
            SplashScreen(navHostController)
        }
        composable(NavigationRoute.SIGNUP.route) {
            SignUpScreen(navHostController)
        }
        composable(NavigationRoute.OTP.route) {
            OtpScreen(navHostController)
        }
        composable(NavigationRoute.HOME.route) {
            viewModel.getAllCategories()
            MainScreen(viewModel)
        }
        composable(NavigationRoute.MENU.route) {
            viewModel.getMenuList()
            MainMenuScreen(viewModel, cartLocalViewModel, navHostController)
        }
        composable(NavigationRoute.CART.route) {
            cartLocalViewModel.getAllCartItems()
            CartScreen(cartLocalViewModel, navHostController)
        }
        composable(NavigationRoute.PROFILE.route) {
            ProfileScreen()
        }
        composable(NavigationRoute.SUBCATEGORY.route) {
            SubCategoryScreen(viewModel, cartLocalViewModel)
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