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
import com.infosys.presentation.viewmodel.UserViewModel
import com.infosys.presentation.viewmodel.HomeViewModel
import com.infosys.presentation.viewmodel.LocalCartViewModel
import com.infosys.presentation.viewmodel.LocalMenuCartViewModel
import com.infosys.presentation.viewmodel.OrdersLocalViewModel
import com.infosys.presentation.viewmodel.MenuViewModel
import com.infosys.presentation.viewmodel.SignupUserViewModel
import com.infosys.utils.enums.LoginType

@Composable
fun BottomNavHost(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel,
    localCartViewModel: LocalCartViewModel,
    localMenuCartViewModel: LocalMenuCartViewModel,
    menuViewModel: MenuViewModel,
    ordersLocalViewModel: OrdersLocalViewModel,
    signupUserViewModel: SignupUserViewModel,
    userViewModel: UserViewModel,
    snackBarHost: SnackbarHostState,
) {
    val user = userViewModel.userInfo.collectAsState().value

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
            SignUpScreen(navHostController, signupUserViewModel)
        }
        composable(NavigationRoute.OTP.route) {
            OtpScreen(navHostController, userViewModel, signupUserViewModel)
        }
        composable(NavigationRoute.HOME.route) {
            Log.e("TAG", "BottomNavHost: HOME")
            menuViewModel.getAllCategories()
            MainScreen(menuViewModel)
        }
        composable(NavigationRoute.MENU.route) {
            homeViewModel.getMenuList()
            MainMenuScreen(homeViewModel, menuViewModel, localMenuCartViewModel, navHostController)
        }
        composable(NavigationRoute.CART.route) {
            localCartViewModel.getAllCartItems()
            userViewModel.readUserInfo()
            CartScreen(localCartViewModel, localMenuCartViewModel, userViewModel, navHostController, snackBarHost)
        }
        composable(NavigationRoute.PROFILE.route) {
            Log.e("TAG", "BottomNavHost: PROFILE")
            userViewModel.readUserInfo()
            ProfileScreen(userViewModel, navHostController)
        }
        composable(NavigationRoute.SUBCATEGORY.route) {
            SubCategoryScreen(menuViewModel, localMenuCartViewModel)
        }
        composable(NavigationRoute.CHECKOUT.route) {
            CheckoutScreen(navHostController, snackBarHost)
        }
        composable(NavigationRoute.ADDRESS.route) {
            localCartViewModel.countCartItems()
            localCartViewModel.grandTotalCartItems()
            SearchAddressScreen(navHostController, localCartViewModel, ordersLocalViewModel)
        }
        composable(NavigationRoute.ORDER.route) {
            ordersLocalViewModel.orderList()
            OrderPlaceScreen(navHostController, ordersLocalViewModel)
        }
        composable(NavigationRoute.ORDER_DETAILS.route) {
            OrderDetailScreen(ordersLocalViewModel)
        }
    }
}