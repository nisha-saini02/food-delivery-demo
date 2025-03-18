package com.infosys.presentation.ui.screens.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.infosys.data.model.cart.Cart
import com.infosys.data.model.category.sub_Category.SubCategory
import com.infosys.utils.enums.CartFunctions
import com.infosys.utils.enums.ItemsCategory
import com.infosys.presentation.ui.screens.utility.ButtonCr
import com.infosys.presentation.ui.screens.utility.Spacer
import com.infosys.presentation.ui.screens.utility.TextTitleMedium
import com.infosys.presentation.ui.screens.listViews.GridListView
import com.infosys.presentation.ui.screens.navigation.NavigationRoute
import com.infosys.presentation.ui.screens.shimmer_effect.ShimmerNavigator
import com.infosys.presentation.ui.screens.utility.TextHeadlineMedium
import com.infosys.presentation.ui.screens.utility.roundShapeCorner
import com.infosys.presentation.viewmodel.LocalCartViewModel
import com.infosys.presentation.viewmodel.LocalMenuCartViewModel
import com.infosys.presentation.viewmodel.UserViewModel
import com.infosys.theme.Orange
import com.infosys.theme.White
import com.infosys.theme.Yellow
import com.infosys.utils.enums.LoginType
import kotlinx.coroutines.launch

@Composable
fun CartScreen(
    localCartViewModel: LocalCartViewModel,
    localMenuCartViewModel: LocalMenuCartViewModel,
    authViewModel: UserViewModel,
    navHostController: NavHostController,
    snackBarHost: SnackbarHostState
) {
    val cart = localCartViewModel.cart.collectAsState().value
    val deleteCart = localMenuCartViewModel.deleteItem.collectAsState().value
    val userInfo = authViewModel.userInfo.collectAsState().value

    val coroutineState = rememberCoroutineScope()

    if (deleteCart > 0) {
        Log.e("TAG", "CartScreen: CART ITEM DELETED")
        localCartViewModel.getAllCartItems()
        localMenuCartViewModel.resetDeleteObserver()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Orange, roundShapeCorner(0, 0, 30, 30))
                .weight(0.15f),
            contentAlignment = Alignment.CenterStart
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            ) {
                TextTitleMedium ("My Cart", color = White)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 110.dp)
                .weight(0.85f),
        ) {
            Column {
                Box (modifier = Modifier.weight(0.85f).padding(16.dp)) {
                    LazyColumn {
                        repeat(10) {
                            item {
                                ShimmerNavigator(NavigationRoute.CART)
                            }
                        }
                    }
                }

                if (!cart.isNullOrEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(modifier = Modifier.weight(0.92f)) {
                            GridListView(
                                cart.map {
                                    SubCategory(
                                        idMeal = it.id,
                                        strMeal = it.name,
                                        strMealThumb = it.thumbnail,
                                        addToCartCount = it.cartCount ?: 1
                                    )
                                },
                                ItemsCategory.CartItems
                            ) { subCategory, count, cartFunction ->
                                val myCart = Cart(
                                    subCategory.idMeal.toString(),
                                    subCategory.strMeal.toString(),
                                    null,
                                    subCategory.strMealThumb.toString(),
                                    count
                                )
                                when (cartFunction) {
                                    CartFunctions.INSERT -> {}
                                    CartFunctions.UPDATE -> {
                                        localMenuCartViewModel.updateItem(myCart)
                                    }

                                    CartFunctions.DELETE -> {
                                        localMenuCartViewModel.deleteItem(myCart)
                                    }
                                }
                            }
                        }

                        Box(modifier = Modifier.weight(0.08f)) {
                            Spacer()
                            ButtonCr(
                                Yellow,
                                text = "Checkout",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            ) {
                                if (userInfo == null || userInfo.type == LoginType.Guest || !userInfo.authenticate) {
                                    coroutineState.launch {
                                        snackBarHost.showSnackbar(
                                            "Please login first to checkout these items.",
                                            null,
                                            true,
                                            SnackbarDuration.Short
                                        )
                                    }
                                } else {
                                    navHostController.navigate(NavigationRoute.CHECKOUT.route)
                                }
                            }
                        }
                    }
                } else {
                    Column (
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TextHeadlineMedium("Cart is Empty", modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }
    }
}