package com.infosys.presentation.ui.screens.listViews

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.infosys.R
import com.infosys.data.model.card.Card
import com.infosys.presentation.ui.screens.main.formatAccountNumber
import com.infosys.presentation.ui.screens.utility.Image
import com.infosys.presentation.ui.screens.utility.Spacer
import com.infosys.presentation.ui.screens.utility.TextLabelLarge
import com.infosys.presentation.ui.screens.utility.TextLabelMedium
import com.infosys.presentation.ui.screens.utility.TextLabelSmall
import com.infosys.presentation.ui.screens.utility.roundShapeCorner
import com.infosys.theme.Blue
import com.infosys.theme.Cream
import com.infosys.theme.Orange
import com.infosys.theme.White
import com.infosys.utils.TaskUtil.getCardType

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardListView(items: List<Card>, event: (Card, Boolean) -> Unit, deleteEvent: (Card) -> Unit) {
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
                    .padding(bottom = 16.dp)) {
                    Column(
                        modifier = Modifier
                            .combinedClickable(
                                onClick = { event.invoke(item, false) },
                                onLongClick = { event.invoke(item, true) }
                            )
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(Orange, Cream, Orange),
                                    start = Offset(0f, 0f),
                                    end = Offset(1000f, 1000f)
                                ), roundShapeCorner()
                            )
                            .padding(16.dp)
                    ) {
                        Row {
                            Column (
                                modifier = if (item.isSelected) Modifier.fillMaxWidth().padding(5.dp)
                                else Modifier.size(0.dp),
                                horizontalAlignment = Alignment.End
                            ){
                                Image(R.drawable.ic_cancel, Modifier.wrapContentSize().clickable { deleteEvent.invoke(item) })
                            }
                            Box(Modifier.weight(0.7f)) {
                                TextLabelMedium(item.holderName.toString())
                            }
                            Box(
                                Modifier
                                    .weight(0.3f)
                                    .background(White, roundShapeCorner(8))
                                    .padding(5.dp),
                                Alignment.Center
                            ) {
                                TextLabelMedium(
                                    getCardType(item.accountNumber.toString()) ?: "error",
                                    Blue, maxLines = Int.MAX_VALUE
                                )
                            }
                        }
                        Spacer()
                        TextLabelLarge(formatAccountNumber(item.accountNumber.toString()))
                        TextLabelSmall("Account number")
                        Spacer()
                        Row {
                            Box(modifier = Modifier.weight(0.5f)) {
                                TextLabelMedium(item.cvv.toString())
                            }
                            Box(modifier = Modifier.weight(0.5f)) {
                                TextLabelMedium(item.expiryMonth.toString()+"/"+item.expiryYear.toString())
                            }
                        }
                        Row {
                            Box(modifier = Modifier.weight(0.5f)) {
                                TextLabelSmall("cvv number")
                            }
                            Box(modifier = Modifier.weight(0.5f)) {
                                TextLabelSmall("Expiry date")
                            }
                        }
                    }
                }
            }
        }
    }
}