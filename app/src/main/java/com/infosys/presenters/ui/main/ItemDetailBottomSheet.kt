package com.infosys.presenters.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.infosys.R
import com.infosys.data.model.category.sub_Category.details.SubCategoryDetails
import com.infosys.presenters.ui.ButtonCr
import com.infosys.presenters.ui.Image
import com.infosys.presenters.ui.LoadImage
import com.infosys.presenters.ui.Spacer
import com.infosys.presenters.ui.TextLabelLarge
import com.infosys.presenters.ui.TextTitleLarge
import com.infosys.presenters.ui.TextTitleMedium
import com.infosys.presenters.ui.TextTitleSmall
import com.infosys.presenters.ui.roundShapeCorner
import com.infosys.presenters.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDetailBottomSheet(
    item: SubCategoryDetails,
    itemPrice: String = "$15",
    count: MutableIntState = remember { mutableIntStateOf(1) },
    isBottomSheetVisible: Boolean,
    sheetState: SheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    ),
    addToCartEvent: (SubCategoryDetails, Int) -> Unit,
    onDismiss: () -> Unit
) {

        if (isBottomSheetVisible) {

            count.value = 0

            ModalBottomSheet(
                onDismissRequest = onDismiss,
                sheetState = sheetState,
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onSurface,
                shape = RectangleShape,
                dragHandle = null,
                scrimColor = Color.Black.copy(alpha = .5f),
//                windowInsets = WindowInsets(0, 0, 0, 0)
            ) {

                Box (modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp)
                    .background(White, roundShapeCorner(20, 20, 0, 0))) {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .verticalScroll(
                                rememberScrollState()
                            ),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TextTitleLarge(item.strMeal.toString()) {}

                        Spacer()

                        LoadImage(item.strMealThumb.toString(), Modifier.size(150.dp))

                        Spacer()

                        TextTitleSmall("Instructions :=\n${item.strInstructions}", maxLines = Int.MAX_VALUE, textAlign = TextAlign.Start)

                        Spacer()

                        Row {
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .weight(0.3f), Alignment.CenterStart) {
                                TextTitleMedium(itemPrice, textAlign = TextAlign.Start)
                            }
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .weight(0.7f), Alignment.CenterEnd) {
                                Row {
                                    Image(R.drawable.ic_remove, modifier = Modifier.wrapContentSize()) {
                                        if (count.value > 1) {
                                            item.addToCartCount -= 1
                                            count.value = item.addToCartCount
                                        }
                                    }
                                    Spacer(8)
                                    TextLabelLarge(text = count.value.toString())
                                    Spacer(8)
                                    Image(R.drawable.ic_add, modifier = Modifier.wrapContentSize()) {
                                        item.addToCartCount += 1
                                        count.value = item.addToCartCount
                                    }
                                }
                            }
                        }

                        Spacer()

                        ButtonCr(text = "Add Item To Cart") {
                            addToCartEvent.invoke(item, count.value)
                            item.addToCartCount = 0
                            count.value = item.addToCartCount

                            onDismiss.invoke()
                        }
                    }
                }

            }

        }

}
