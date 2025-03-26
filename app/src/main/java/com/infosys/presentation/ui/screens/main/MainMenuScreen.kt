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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.infosys.presentation.ui.screens.utility.EditTextBodyMedium
import com.infosys.presentation.ui.screens.utility.Image
import com.infosys.presentation.ui.screens.utility.Spacer
import com.infosys.presentation.ui.screens.utility.TextHeadlineSmall
import com.infosys.presentation.ui.screens.utility.TextTitleMedium
import com.infosys.presentation.ui.screens.listViews.GridListView
import com.infosys.presentation.ui.screens.utility.roundShapeCorner
import com.infosys.theme.Orange
import com.infosys.theme.White
import com.infosys.presentation.ui.screens.listViews.HorizontalCategoriesListView
import com.infosys.presentation.ui.screens.listViews.MainMenuListView
import com.infosys.presentation.ui.screens.navigation.Main
import com.infosys.presentation.ui.screens.navigation.MainMenu
import com.infosys.presentation.ui.screens.navigation.MainMenuHorizontal
import com.infosys.presentation.ui.screens.navigation.SubCategory
import com.infosys.presentation.ui.screens.shimmer_effect.ShimmerNavigator
import com.infosys.presentation.viewmodel.HomeViewModel
import com.infosys.presentation.viewmodel.LocalMenuCartViewModel
import com.infosys.presentation.viewmodel.MenuViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuScreen(
    viewModel: HomeViewModel,
    menuViewModel: MenuViewModel,
    localMenuCartViewModel: LocalMenuCartViewModel,
    navigationHostController: NavHostController
) {
    val search = remember { mutableStateOf("") }
    val categories = menuViewModel.categories.collectAsState().value?.categories
    val meals = viewModel.meals.collectAsState().value

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
                        navigationHostController.navigate(Main)
                    }
                }

                Spacer()

                if (categories.isNullOrEmpty()) {
                    LazyRow {
                        repeat(6) {
                            item {
                                ShimmerNavigator(MainMenuHorizontal::class.simpleName)
                            }
                        }
                    }
                }
                categories?.let {
                    HorizontalCategoriesListView(it) { category ->
                        menuViewModel.getSubCategories(category.strCategory.toString())
                        menuViewModel.category.value = category.strCategory.toString()
                        navigationHostController.navigate(SubCategory)
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

                if (meals.data?.meals.isNullOrEmpty()) {
                    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                        repeat(6) {
                            item {
                                ShimmerNavigator(MainMenu::class.simpleName)
                            }
                        }
                    }
                }
                meals.data?.meals?.let {
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
                            localMenuCartViewModel.insertItem(
                                Cart(
                                    subCategoryDetails.idMeal.toString(),
                                    subCategoryDetails.strMeal.toString(),
                                    subCategoryDetails.strInstructions.toString(),
                                    subCategoryDetails.strMealThumb.toString(),
                                    count
                                )
                            )

                            Log.e("Local", "MainMenuScreen: inserted item")
                        }
                    )
                }

                ItemDetailBottomSheet(
                    item.value,
                    isBottomSheetVisible = isBottomSheetVisible,
                    sheetState = sheetState,
                    addToCartEvent = { subCategoryDetails, count ->
                        localMenuCartViewModel.insertItem(
                            Cart(
                                subCategoryDetails.idMeal.toString(),
                                subCategoryDetails.strMeal.toString(),
                                subCategoryDetails.strInstructions.toString(),
                                subCategoryDetails.strMealThumb.toString(),
                                count
                            )
                        )

                        Log.e("Local", "MainMenuScreen: inserted item")
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
fun SubCategoryScreen(
    viewModel: MenuViewModel,
    localMenuCartViewModel: LocalMenuCartViewModel
) {
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
                if (subCategories?.meals.isNullOrEmpty()) {
                    Column (modifier = Modifier.padding(16.dp)) {
                        LazyColumn {
                            repeat(10) {
                                item {
                                    ShimmerNavigator(SubCategory::class.simpleName)
                                }
                            }
                        }
                    }
                }
                subCategories?.meals?.let {
                    GridListView(it, ItemsCategory.SubCategoryList) { subCategory, count, cartFunction ->
                        if(cartFunction == CartFunctions.INSERT) {
                            localMenuCartViewModel.insertItem(
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