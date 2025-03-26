package com.infosys.presentation.ui.screens.navigation

import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.infosys.data.firebase.FirebaseAuthentication
import com.infosys.data.firebase.FirebaseFirestore
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
import com.infosys.presentation.ui.screens.onboarding.SignInScreen
import com.infosys.presentation.ui.screens.onboarding.SignUpScreen
import com.infosys.presentation.viewmodel.CardInfoViewModel
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
    cardInfoViewModel: CardInfoViewModel,
    homeViewModel: HomeViewModel,
    localCartViewModel: LocalCartViewModel,
    localMenuCartViewModel: LocalMenuCartViewModel,
    menuViewModel: MenuViewModel,
    ordersLocalViewModel: OrdersLocalViewModel,
    signupUserViewModel: SignupUserViewModel,
    userViewModel: UserViewModel,
    snackBarHost: SnackbarHostState,
    objFirebase: FirebaseAuthentication,
    objFirebaseFirestore: FirebaseFirestore
) {
    val user = userViewModel.userInfo.collectAsState().value

    NavHost(
        navController = navHostController,
        startDestination =
        if (user == null || user.type == LoginType.Guest) {
            Splash
        } else if (!user.authenticate) {
            Otp
        } else {
            Main
        }
    ) {
        composable<Splash> {
            //fetchGsonData = it.toRoute<Splash>()
            SplashScreen(navHostController)
        }
        composable<SignUp> {
            SignUpScreen(navHostController, signupUserViewModel, objFirebase, objFirebaseFirestore)
        }
        composable<SignIn> {
            SignInScreen(navHostController, signupUserViewModel, objFirebase, objFirebaseFirestore)
        }
        composable<Otp> {
            OtpScreen(navHostController, userViewModel, signupUserViewModel)
        }
        composable<Main> {
            Log.e("TAG", "BottomNavHost: HOME")
            menuViewModel.getAllCategories()
            MainScreen(menuViewModel)
        }
        composable<MainMenu> {
            homeViewModel.getMenuList()
            MainMenuScreen(homeViewModel, menuViewModel, localMenuCartViewModel, navHostController)
        }
        composable<Cart> {
            localCartViewModel.getAllCartItems()
            userViewModel.readUserInfo()
            CartScreen(localCartViewModel, localMenuCartViewModel, userViewModel, navHostController, snackBarHost)
        }
        composable<Profile> {
            Log.e("TAG", "BottomNavHost: PROFILE")
            userViewModel.readUserInfo()
            ProfileScreen(userViewModel, navHostController, objFirebaseFirestore, signupUserViewModel, snackBarHost)
        }
        composable<SubCategory> {
            SubCategoryScreen(menuViewModel, localMenuCartViewModel)
        }
        composable<Checkout> {
            cardInfoViewModel.getAllCards()
            CheckoutScreen(navHostController, snackBarHost, cardInfoViewModel)
        }
        composable<SearchAddress> {
            localCartViewModel.countCartItems()
            localCartViewModel.grandTotalCartItems()
            SearchAddressScreen(navHostController, localCartViewModel, ordersLocalViewModel)
        }
        composable<OrderPlace> {
            ordersLocalViewModel.orderList()
            OrderPlaceScreen(navHostController, ordersLocalViewModel)
        }
        composable<OrderDetail> {
            OrderDetailScreen(ordersLocalViewModel)
        }
    }
}