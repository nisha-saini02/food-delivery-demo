package com.infosys.presentation.ui.screens.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.infosys.R
import com.infosys.data.model.order.Order
import com.infosys.presentation.ui.screens.navigation.NavigationRoute
import com.infosys.presentation.ui.screens.utility.ButtonCr
import com.infosys.presentation.ui.screens.utility.EditTextBodyMedium
import com.infosys.presentation.ui.screens.utility.HorizontalLine
import com.infosys.presentation.ui.screens.utility.Image
import com.infosys.presentation.ui.screens.utility.Spacer
import com.infosys.presentation.ui.screens.utility.TextLabelLarge
import com.infosys.presentation.ui.screens.utility.TextLabelMedium
import com.infosys.presentation.ui.screens.utility.TextLabelSmall
import com.infosys.presentation.ui.screens.utility.roundShapeCorner
import com.infosys.presentation.viewmodel.LocalCartViewModel
import com.infosys.presentation.viewmodel.OrdersLocalViewModel
import com.infosys.theme.Black
import com.infosys.theme.Gray
import com.infosys.theme.Orange
import com.infosys.theme.White
import com.infosys.theme.Yellow

@Composable
fun SearchAddressScreen(
    navHostController: NavHostController,
    localCartViewModel: LocalCartViewModel,
    ordersLocalViewModel: OrdersLocalViewModel
) {

    val search = remember { mutableStateOf("") }
    val orderPlace = remember { mutableStateOf(false) }
    val countCartItems = localCartViewModel.countCartItems.collectAsState().value
    val grandTotalCartItems = localCartViewModel.grandTotalCartItems.collectAsState().value

    Column(
        modifier =
        if (orderPlace.value) Modifier.size(0.dp)
        else Modifier.fillMaxSize().background(White)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Orange, roundShapeCorner(0, 0, 30, 30))
                .weight(0.25f),
            contentAlignment = Alignment.CenterStart
        ) {
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            ) {
                Image(
                    id = R.drawable.ic_cancel,
                    modifier = Modifier.size(25.dp)
                ) {
                    navHostController.popBackStack()
                }

                Row (verticalAlignment = Alignment.CenterVertically) {
                    Image(R.drawable.ic_start_location, Modifier.wrapContentSize())
                    TextLabelMedium ("Subway, Rayya", modifier = Modifier.fillMaxWidth().padding(16.dp), textAlign = TextAlign.Start)
                }

                HorizontalLine()

                Row (verticalAlignment = Alignment.CenterVertically) {
                    Image(R.drawable.ic_destination, Modifier.wrapContentSize())
                    EditTextBodyMedium(
                        search.value,
                        hint = "Choose your location",
                        textColor = Black,
                        hintColor = Black,
                    ) {
                        search.value = it
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 110.dp, start = 16.dp, end = 16.dp, top = 16.dp)
                .weight(0.75f),
        ) {

            Column {
                LocationItems(
                    listOfCities.filter { it.name.contains(search.value, ignoreCase = true) }
                ) {
                    ordersLocalViewModel.insertOrderItem(
                        Order(
                            orderGrandTotal = grandTotalCartItems,
                            orderItems = countCartItems,
                            destinationLat = it.lat,
                            destinationLong = it.long
                        )
                    )

                    ordersLocalViewModel.deleteAllCarts()

                    orderPlace.value = true
                }
            }
        }
    }

    Column(
        modifier =
        if (!orderPlace.value) Modifier.size(0.dp)
        else Modifier.fillMaxSize().background(White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        BackHandler {
            navHostController.popBackStack(NavigationRoute.CHECKOUT.route, inclusive = true)
        }

        Image(
            R.drawable.success_checkout,
            Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Yellow, Orange),
                        start = Offset(0f, 0f),
                        end = Offset(1000f, 1000f)
                    ), roundShapeCorner(100)
                )
                .padding(20.dp)
                .size(150.dp)
        )

        Spacer()

        TextLabelLarge("Successfully placed an Order", modifier = Modifier.fillMaxWidth())

        Spacer()

        ButtonCr(text = "Track Order") {
            navHostController.navigate(NavigationRoute.ORDER.route){
                popUpTo(NavigationRoute.CHECKOUT.route){
                    inclusive = true
                }
            }
        }
    }
}

@Composable
fun LocationItems(items: List<MyAddress>, clickEvent: (MyAddress) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 500.dp)
    ) {
        LazyVerticalGrid (
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(5.dp)
        ) {
            items(items) { item ->
                Column (
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(vertical = 8.dp)
                        .clickable { clickEvent(item) },
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row (
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(R.drawable.ic_location, Modifier.wrapContentSize())
                        Spacer(5)
                        TextLabelSmall(text = item.name)
                    }
                    Spacer(5)
                    HorizontalLine(color = Gray)
                }
            }
        }
    }
}

val listOfCities = listOf(
    MyAddress("Sunny Enclave, Kharar, India", 30.7110, 76.7720),
    MyAddress("Palm Village, Mohali, India", 30.7115, 76.7815),
    MyAddress("Royal Enfield, Kharar, India", 30.7408168,76.6749254),
    MyAddress("Ivory Hotel, Kharar, India", 30.7420516,76.6731869),
    MyAddress("Arista Hotel, Kharar, India", 30.7420516,76.6731869),
    MyAddress("Prasad's Niwas, Kharar, India", 30.7429599,76.6729616),
    MyAddress("Desu Majra, Kharar, India", 30.7427362,76.671127),
)

data class MyAddress(val name: String, val lat: Double, val long: Double)