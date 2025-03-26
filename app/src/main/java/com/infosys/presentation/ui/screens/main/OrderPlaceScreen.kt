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
import com.infosys.presentation.ui.screens.listViews.OrderListView
import com.infosys.presentation.ui.screens.navigation.OrderDetail
import com.infosys.presentation.ui.screens.navigation.OrderPlace
import com.infosys.presentation.ui.screens.shimmer_effect.ShimmerNavigator
import com.infosys.presentation.ui.screens.utility.TextHeadlineMedium
import com.infosys.presentation.ui.screens.utility.TextTitleMedium
import com.infosys.presentation.ui.screens.utility.roundShapeCorner
import com.infosys.presentation.viewmodel.OrdersLocalViewModel
import com.infosys.theme.Orange
import com.infosys.theme.White

@Composable
fun OrderPlaceScreen(navHostController: NavHostController, localViewModel: OrdersLocalViewModel) {
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
                Box (modifier = Modifier.weight(0.85f).padding(16.dp)) {
                    LazyColumn {
                        repeat(10) {
                            item {
                                ShimmerNavigator(OrderPlace::class.simpleName)
                            }
                        }
                    }
                }
                if (!orders.isNullOrEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OrderListView(
                            orders,
                        ) { orderItem ->
                            localViewModel.getOrder(orderItem.id.toString())
                            navHostController.navigate(OrderDetail)
                        }
                    }
                } else {
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