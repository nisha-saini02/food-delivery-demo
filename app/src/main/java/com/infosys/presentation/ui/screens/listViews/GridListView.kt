package com.infosys.presentation.ui.screens.listViews

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.infosys.R
import com.infosys.data.model.category.Category
import com.infosys.data.model.category.sub_Category.SubCategory
import com.infosys.data.model.category.sub_Category.details.SubCategoryDetails
import com.infosys.utils.enums.CartFunctions
import com.infosys.utils.enums.ItemsCategory
import com.infosys.presentation.ui.screens.ButtonCr
import com.infosys.presentation.ui.screens.Image
import com.infosys.presentation.ui.screens.LoadImage
import com.infosys.presentation.ui.screens.Spacer
import com.infosys.presentation.ui.screens.TextHeadlineMedium
import com.infosys.presentation.ui.screens.TextHeadlineSmall
import com.infosys.presentation.ui.screens.TextLabelLarge
import com.infosys.presentation.ui.screens.TextLabelSmall
import com.infosys.presentation.ui.screens.TextTitleMedium
import com.infosys.presentation.ui.screens.TextTitleSmall
import com.infosys.presentation.ui.screens.roundShapeCorner
import com.infosys.theme.Yellow

@Composable
fun GridListView(items: List<SubCategory>, category: ItemsCategory, cartEvent: (SubCategory, Int, CartFunctions) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = if (category == ItemsCategory.CategoryItems) 300.dp else Int.MAX_VALUE.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(
                when(category) {
                    ItemsCategory.CategoryItems -> 2
                    ItemsCategory.SubCategoryList -> 1
                    ItemsCategory.CartItems -> 1
                }
            ),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(items) { item ->
                val count = remember { mutableIntStateOf(item.addToCartCount) }
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when(category) {
                        ItemsCategory.CategoryItems -> {
                            LoadImage(
                                item.strMealThumb.toString(),
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(bottom = 8.dp)
                            )
                            TextHeadlineSmall(text = item.strMeal.toString())
                        }
                        ItemsCategory.SubCategoryList -> {
                            Row (modifier = Modifier
                                .fillMaxWidth()
                                .border(BorderStroke(1.dp, Yellow), roundShapeCorner())) {
                                Box (modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp)
                                    .border(BorderStroke(1.dp, Yellow), roundShapeCorner())
                                    .weight(0.4f), Alignment.Center) {
                                    LoadImage(
                                        item.strMealThumb.toString(),
                                        modifier = Modifier
                                            .size(width = 100.dp, height = 80.dp)
                                            .padding(8.dp)
                                    )
                                }
                                Box (modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp)
                                    .weight(0.6f)) {
                                    Column {
                                        TextTitleSmall(
                                            item.strMeal.toString(),
                                            textAlign = TextAlign.Start
                                        )
                                        Spacer()
                                        Box {
                                            Row {
                                                TextTitleMedium("$3", textAlign = TextAlign.Start, modifier = Modifier.weight(0.6f))
                                                Row (modifier = Modifier.weight(0.6f)) {
                                                    Image(R.drawable.ic_remove, modifier = Modifier.wrapContentSize()) {
                                                        if (count.value >= 1) {
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
                                        Spacer(if (count.value > 0) 16 else 0)
                                        ButtonCr(
                                            text = "Add To Cart",
                                            modifier =
                                            if (count.value > 0)
                                                Modifier.wrapContentSize()
                                            else Modifier.size(0.dp)) {
                                            cartEvent.invoke(item, item.addToCartCount, CartFunctions.INSERT)

                                            //reset ui
                                            item.addToCartCount = 0
                                            count.value = item.addToCartCount
                                        }
                                    }
                                }
                            }
                        }
                        ItemsCategory.CartItems -> {
                            Row (modifier = Modifier
                                .fillMaxWidth()
                                .border(BorderStroke(1.dp, Yellow), roundShapeCorner())) {
                                Box (modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp)
                                    .border(BorderStroke(1.dp, Yellow), roundShapeCorner())
                                    .weight(0.4f), Alignment.Center) {
                                    LoadImage(
                                        item.strMealThumb.toString(),
                                        modifier = Modifier
                                            .size(width = 100.dp, height = 80.dp)
                                            .padding(8.dp)
                                    )
                                }
                                Box (modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp)
                                    .weight(0.6f)) {
                                    Column {
                                        TextTitleSmall(
                                            item.strMeal.toString(),
                                            textAlign = TextAlign.Start
                                        )
                                        Spacer()
                                        Box {
                                            Row {
                                                TextTitleMedium("$${3*count.value}", textAlign = TextAlign.Start, modifier = Modifier.weight(0.6f))
                                                Row (modifier = Modifier.weight(0.6f)) {
                                                    Image(R.drawable.ic_remove, modifier = Modifier.wrapContentSize()) {
                                                        if (count.value > 1) {
                                                            item.addToCartCount -= 1
                                                            count.value = item.addToCartCount

                                                            cartEvent.invoke(item, item.addToCartCount, CartFunctions.UPDATE)
                                                        } else {
                                                            cartEvent.invoke(item, item.addToCartCount, CartFunctions.DELETE)
                                                        }
                                                    }
                                                    Spacer(8)
                                                    TextLabelLarge(text = count.value.toString())
                                                    Spacer(8)
                                                    Image(R.drawable.ic_add, modifier = Modifier.wrapContentSize()) {
                                                        item.addToCartCount += 1
                                                        count.value = item.addToCartCount

                                                        cartEvent.invoke(item, item.addToCartCount, CartFunctions.UPDATE)
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}