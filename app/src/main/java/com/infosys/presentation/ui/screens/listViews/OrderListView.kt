package com.infosys.presentation.ui.screens.listViews

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.infosys.data.model.order.Order
import com.infosys.presentation.ui.screens.utility.Spacer
import com.infosys.presentation.ui.screens.utility.TextHeadlineLarge
import com.infosys.presentation.ui.screens.utility.TextHeadlineSmall
import com.infosys.presentation.ui.screens.utility.TextLabelMedium
import com.infosys.presentation.ui.screens.utility.roundShapeCorner
import com.infosys.theme.Yellow

@Composable
fun OrderListView(items: List<Order>, cartEvent: (Order) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = Int.MAX_VALUE.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(items) { item ->
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .border(BorderStroke(1.dp, Yellow), roundShapeCorner())
                    .padding(16.dp)) {

                    Row (verticalAlignment = Alignment.CenterVertically) {
                        TextHeadlineLarge("Order ID: ")
                        TextHeadlineSmall("ORD"+item.id.toString())
                    }
                    Spacer()

                    Row {
                        Box (Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                            TextLabelMedium("Total Items: "+item.orderItems)
                        }
                        Box (Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
                            TextLabelMedium("Grant Total: "+item.orderGrandTotal)
                        }
                    }
                }
            }
        }
    }
}