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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.infosys.R
import com.infosys.data.model.cart.Cart
import com.infosys.data.model.category.sub_Category.details.SubCategoryDetails
import com.infosys.utils.enums.CartFunctions
import com.infosys.utils.enums.ItemsCategory
import com.infosys.presentation.ui.screens.EditTextBodyMedium
import com.infosys.presentation.ui.screens.Image
import com.infosys.presentation.ui.screens.Spacer
import com.infosys.presentation.ui.screens.TextHeadlineSmall
import com.infosys.presentation.ui.screens.TextTitleMedium
import com.infosys.presentation.ui.screens.listViews.GridListView
import com.infosys.presentation.ui.screens.roundShapeCorner
import com.infosys.theme.Orange
import com.infosys.theme.White
import com.infosys.presentation.ui.screens.listViews.HorizontalCategoriesListView
import com.infosys.presentation.ui.screens.listViews.MainMenuListView
import com.infosys.presentation.ui.screens.navigation.NavigationRoute
import com.infosys.presentation.viewmodel.CartLocalViewModel
import com.infosys.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuScreen(viewModel: MainViewModel, cartLocalViewModel: CartLocalViewModel, navigationHostController: NavHostController) {
    val search = remember { mutableStateOf("") }
    val categories = viewModel.categories.collectAsState().value
    val meals = viewModel.meals.collectAsState().value
    val insertItem = cartLocalViewModel.insertItem.collectAsState().value

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
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
                ) {

                Image(
                    id = R.drawable.ic_search,
                    modifier = Modifier.size(25.dp)
                )

                EditTextBodyMedium (search.value, hint = "Search your favorite dish") {
                    search.value = it
                    if (search.value.length >= 3) {
                        viewModel.getMenuList(search.value)
                    } else if (search.value.isEmpty()) {
                        viewModel.getMenuList(search.value)
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 85.dp)
                .weight(0.85f),
        ) {
            Column {

                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextHeadlineSmall(
                        "All Categories",
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.9f),
                        textAlign = TextAlign.Start
                    )
                    Image(
                        R.drawable.ic_arrow_forward,
                        modifier = Modifier
                            .wrapContentSize()
                            .weight(0.1f)
                    ) {
                        navigationHostController.navigate(NavigationRoute.HOME.route)
                    }
                }

                Spacer()

                categories.data?.let {
                    HorizontalCategoriesListView(it) {
                        viewModel.getSubCategories(it.strCategory.toString())
                        viewModel.category.value = it.strCategory.toString()
                        navigationHostController.navigate(NavigationRoute.SUBCATEGORY.route)
                    }
                }

                Spacer()

                TextHeadlineSmall("All Items")

                Spacer()

                val scope = rememberCoroutineScope()
                var isBottomSheetVisible by rememberSaveable { mutableStateOf(false) }
                val sheetState = rememberModalBottomSheetState(
                    skipPartiallyExpanded = true
                )
                val item = remember { mutableStateOf(SubCategoryDetails()) }

                meals.data?.let {
                    MainMenuListView(
                        it,
                        clickEvent = { subCategoryDetails ->
                            item.value = subCategoryDetails
                            scope.launch {
                                isBottomSheetVisible = true
                                sheetState.expand()
                            }
                        },
                        addToCartEvent = { subCategoryDetails, count ->
                            cartLocalViewModel.insertItem(
                                Cart(
                                    subCategoryDetails.idMeal.toString(),
                                    subCategoryDetails.strMeal.toString(),
                                    subCategoryDetails.strInstructions.toString(),
                                    subCategoryDetails.strMealThumb.toString(),
                                    count
                                )
                            )

                            if (insertItem.data != null) {
                                Log.e("Local", "MainMenuScreen: inserted item")
                            }
                        }
                    )
                }

                ItemDetailBottomSheet(
                    item.value,
                    isBottomSheetVisible = isBottomSheetVisible,
                    sheetState = sheetState,
                    addToCartEvent = { subCategoryDetails, count ->
                        cartLocalViewModel.insertItem(
                            Cart(
                                subCategoryDetails.idMeal.toString(),
                                subCategoryDetails.strMeal.toString(),
                                subCategoryDetails.strInstructions.toString(),
                                subCategoryDetails.strMealThumb.toString(),
                                count
                            )
                        )

                        if (insertItem.data != null) {
                            Log.e("Local", "MainMenuScreen: inserted item")
                        }
                    }
                ) {
                    scope.launch { sheetState.hide() }
                        .invokeOnCompletion { isBottomSheetVisible = false }
                }
            }
        }
    }
}

@Composable
fun SubCategoryScreen(viewModel: MainViewModel, cartLocalViewModel: CartLocalViewModel) {
    val subCategories = viewModel.subcategories.collectAsState().value
    val category = viewModel.category.collectAsState().value

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
                TextTitleMedium (category, color = White)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp, bottom = 50.dp)
                .weight(0.85f),
        ) {
            Column {
                subCategories.data?.let {
                    GridListView(it, ItemsCategory.SubCategoryList) { subCategory, count, cartFunction ->
                        if(cartFunction == CartFunctions.INSERT) {
                            cartLocalViewModel.insertItem(
                                Cart(
                                    subCategory.idMeal.toString(),
                                    subCategory.strMeal.toString(),
                                    null,
                                    subCategory.strMealThumb.toString(),
                                    count
                                )
                            )
                            Log.e("Local", "MainMenuScreen: inserting item")
                        }
                    }
                }
            }
        }
    }
}