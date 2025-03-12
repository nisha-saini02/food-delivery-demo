package com.infosys.presentation.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.infosys.data.remote.Resource
import com.infosys.presentation.ui.screens.listViews.OrderListView
import com.infosys.presentation.ui.screens.navigation.NavigationRoute
import com.infosys.presentation.ui.screens.shimmer_effect.ShimmerNavigator
import com.infosys.presentation.ui.screens.utility.TextHeadlineMedium
import com.infosys.presentation.ui.screens.utility.TextTitleMedium
import com.infosys.presentation.ui.screens.utility.roundShapeCorner
import com.infosys.presentation.viewmodel.LocalViewModel
import com.infosys.theme.Orange
import com.infosys.theme.White

@Composable
fun OrderPlaceScreen(navHostController: NavHostController, localViewModel: LocalViewModel) {
    val orders = localViewModel.orders.collectAsState().value

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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            ) {
                TextTitleMedium("My Orders", color = White)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 110.dp)
                .weight(0.85f),
        ) {
            Column {
                if (orders.data.isNullOrEmpty()) {
                    Box (modifier = Modifier.weight(0.85f).padding(16.dp)) {
                        LazyColumn {
                            repeat(10) {
                                item {
                                    ShimmerNavigator(NavigationRoute.ORDER)
                                }
                            }
                        }
                    }
                }
                if (orders is Resource.Success && !orders.data.isNullOrEmpty()) {
                    OrderListView(
                        orders.data,
                    ) { orderItem ->
                        localViewModel.getOrder(orderItem.id.toString())
                        navHostController.navigate(NavigationRoute.ORDER_DETAILS.route)
                    }
                } else if (orders is Resource.Success || orders is Resource.Error) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TextHeadlineMedium("Order is Empty", modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }

    }
}