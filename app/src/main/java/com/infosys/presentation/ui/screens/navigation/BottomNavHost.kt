package com.infosys.presentation.ui.screens.navigation

import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.infosys.presentation.ui.screens.main.OrderPlaceScreen
import com.infosys.presentation.ui.screens.map.OrderDetailScreen
import com.infosys.presentation.ui.screens.onboarding.OtpScreen
import com.infosys.presentation.ui.screens.onboarding.SignUpScreen
import com.infosys.presentation.viewmodel.AuthViewModel
import com.infosys.presentation.viewmodel.LocalViewModel
import com.infosys.presentation.viewmodel.MainViewModel
import com.infosys.utils.enums.LoginType

@Composable
fun BottomNavHost(
    navHostController: NavHostController,
    viewModel: MainViewModel,
    cartLocalViewModel: LocalViewModel,
    authViewModel: AuthViewModel,
    snackBarHost: SnackbarHostState,
) {
    val user = authViewModel.userInfo.collectAsState().value

    NavHost(
        navController = navHostController,
        startDestination =
        if (user == null || user.type == LoginType.Guest) {
            NavigationRoute.SPLASH.route
        } else if (!user.authenticate) {
            NavigationRoute.OTP.route
        } else {
            NavigationRoute.HOME.route
        }
    ) {
        composable(NavigationRoute.SPLASH.route) {
            SplashScreen(navHostController)
        }
        composable(NavigationRoute.SIGNUP.route) {
            SignUpScreen(navHostController, authViewModel)
        }
        composable(NavigationRoute.OTP.route) {
            OtpScreen(navHostController, authViewModel)
        }
        composable(NavigationRoute.HOME.route) {
            Log.e("TAG", "BottomNavHost: HOME")
            viewModel.getAllCategories()
            MainScreen(viewModel)
        }
        composable(NavigationRoute.MENU.route) {
            viewModel.getMenuList()
            MainMenuScreen(viewModel, cartLocalViewModel, navHostController)
        }
        composable(NavigationRoute.CART.route) {
            cartLocalViewModel.getAllCartItems()
            authViewModel.readUserInfo()
            CartScreen(cartLocalViewModel, authViewModel, navHostController, snackBarHost)
        }
        composable(NavigationRoute.PROFILE.route) {
            Log.e("TAG", "BottomNavHost: PROFILE")
            authViewModel.readUserInfo()
            ProfileScreen(authViewModel, navHostController)
        }
        composable(NavigationRoute.SUBCATEGORY.route) {
            SubCategoryScreen(viewModel, cartLocalViewModel)
        }
        composable(NavigationRoute.CHECKOUT.route) {
            CheckoutScreen(navHostController, snackBarHost)
        }
        composable(NavigationRoute.ADDRESS.route) {
            cartLocalViewModel.countCartItems()
            cartLocalViewModel.grandTotalCartItems()
            SearchAddressScreen(navHostController, cartLocalViewModel)
        }
        composable(NavigationRoute.ORDER.route) {
            cartLocalViewModel.orderList()
            OrderPlaceScreen(navHostController, cartLocalViewModel)
        }
        composable(NavigationRoute.ORDER_DETAILS.route) {
            OrderDetailScreen(cartLocalViewModel)
        }
    }
}