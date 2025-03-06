package com.infosys.presentation.ui.screens.listViews

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.infosys.R
import com.infosys.data.model.category.sub_Category.details.SubCategoryDetails
import com.infosys.presentation.ui.screens.utility.ButtonCr
import com.infosys.presentation.ui.screens.utility.Image
import com.infosys.presentation.ui.screens.utility.LoadImage
import com.infosys.presentation.ui.screens.utility.Spacer
import com.infosys.presentation.ui.screens.utility.TextHeadlineMedium
import com.infosys.presentation.ui.screens.utility.TextLabelLarge
import com.infosys.presentation.ui.screens.utility.roundShapeCorner
import com.infosys.theme.Yellow

@Composable
fun MainMenuListView(items: List<SubCategoryDetails>, spanCount: Int = 2, clickEvent: (SubCategoryDetails) -> Unit, addToCartEvent: (SubCategoryDetails, Int) -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(spanCount),
//            contentPadding = PaddingValues(16.dp)
        ) {
            items(items) { item ->
                val count = remember { mutableIntStateOf(item.addToCartCount) }

                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .border(BorderStroke(1.dp, Yellow), roundShapeCorner()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer()
                    LoadImage(
                        item.strMealThumb.toString(),
                        modifier = Modifier
                            .size(100.dp)
                    ) {
                        clickEvent.invoke(item)
                    }
                    Spacer(5)
                    TextHeadlineMedium(text = item.strMeal.toString())
                    TextLabelLarge(text = item.strArea.toString())
                    Spacer()

                    Row {
                        Image(R.drawable.ic_remove, modifier = Modifier.wrapContentSize()) {
                            if (count.value > 0) {
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

                    Spacer(if (count.value > 0) 16 else 0)

                    ButtonCr(
                        text = "Add To Cart",
                        modifier =
                        if (count.value > 0)
                            Modifier.wrapContentSize()
                        else Modifier.size(0.dp)) {
                        addToCartEvent.invoke(item, item.addToCartCount)

                        //reset ui
                        item.addToCartCount = 0
                        count.value = item.addToCartCount
                    }

                    Spacer()
                }
            }
        }
    }
}