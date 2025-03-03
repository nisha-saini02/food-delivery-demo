package com.infosys.presenters.ui.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.infosys.data.model.cart.Cart
import com.infosys.data.model.category.sub_Category.SubCategory
import com.infosys.data.model.enums.CartFunctions
import com.infosys.data.model.enums.ItemsCategory
import com.infosys.data.resources.Resource
import com.infosys.presenters.ui.ButtonCr
import com.infosys.presenters.ui.Spacer
import com.infosys.presenters.ui.TextTitleMedium
import com.infosys.presenters.ui.listViews.GridListView
import com.infosys.presenters.ui.navigation.navigationItems
import com.infosys.presenters.ui.roundShapeCorner
import com.infosys.presenters.ui.theme.Orange
import com.infosys.presenters.ui.theme.White
import com.infosys.presenters.ui.theme.Yellow
import com.infosys.presenters.viewmodel.LocalViewModel

@Composable
fun CartScreen(localViewModel: LocalViewModel, navHostController: NavHostController) {
    val cart = localViewModel.cart.collectAsState().value

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
                if (cart is Resource.Success && !cart.data.isNullOrEmpty()) {
                    Log.e("Local", "CartScreen: ${cart.data}")

                    Box (modifier = Modifier.weight(0.85f)) {
                        GridListView(
                            cart.data.map { SubCategory(idMeal = it.id, strMeal = it.name, strMealThumb = it.thumbnail, addToCartCount = it.cartCount ?: 1) },
                            ItemsCategory.CartItems
                        ) { subCategory, count, cartFunction ->
                            val myCart = Cart(subCategory.idMeal.toString(), subCategory.strMeal.toString(), null, subCategory.strMealThumb.toString(), count)
                            when(cartFunction) {
                                CartFunctions.INSERT -> TODO("Insertion item to cart is pending")
                                CartFunctions.UPDATE -> {
                                    localViewModel.updateItem(myCart)
                                }
                                CartFunctions.DELETE -> {
                                    localViewModel.deleteItem(myCart)
                                    localViewModel.getAllCartItems()
                                }
                            }
                        }
                    }

                    Box (modifier = Modifier.weight(0.15f)) {
                        Spacer()
                        ButtonCr(
                            Yellow,
                            text = "Checkout",
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                        ) {
                            navHostController.navigate(navigationItems[5].name)
                        }
                    }
                }
            }
        }
    }
}