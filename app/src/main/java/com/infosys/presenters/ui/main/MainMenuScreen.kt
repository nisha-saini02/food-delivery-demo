package com.infosys.presenters.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import com.infosys.data.model.category.sub_Category.details.SubCategoryDetails
import com.infosys.presenters.ui.EditTextBodyMedium
import com.infosys.presenters.ui.Image
import com.infosys.presenters.ui.Spacer
import com.infosys.presenters.ui.TextHeadlineSmall
import com.infosys.presenters.ui.TextTitleMedium
import com.infosys.presenters.ui.listViews.GridListView
import com.infosys.presenters.ui.roundShapeCorner
import com.infosys.presenters.ui.theme.Orange
import com.infosys.presenters.ui.theme.White
import com.infosys.presenters.ui.listViews.HorizontalCategoriesListView
import com.infosys.presenters.ui.listViews.MainMenuListView
import com.infosys.presenters.ui.navigation.navigationItems
import com.infosys.presenters.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuScreen(paddingValues: PaddingValues, viewModel: MainViewModel, navigationHostController: NavHostController) {
    val search = remember { mutableStateOf("") }
    val categories = viewModel.categories.collectAsState().value
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
                .padding(16.dp)
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
                    )
                }

                Spacer()

                categories.data?.let {
                    HorizontalCategoriesListView(it) {
                        viewModel.getSubCategories(it.strCategory.toString())
                        viewModel.category.value = it.strCategory.toString()
                        navigationHostController.navigate(navigationItems[4].name)
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
                    MainMenuListView(it) { subCategoryDetails ->
                        item.value = subCategoryDetails
                        scope.launch {
                            isBottomSheetVisible = true
                            sheetState.expand()
                        }
                    }
                }

                ItemDetailBottomSheet(
                    item.value.strMeal.toString(),
                    item.value.strInstructions.toString(),
                    item.value.strMealThumb.toString(),
                    isBottomSheetVisible = isBottomSheetVisible,
                    sheetState = sheetState,
                ) {
                    scope.launch { sheetState.hide() }
                        .invokeOnCompletion { isBottomSheetVisible = false }
                }
            }
        }
    }
}

@Composable
fun SubCategoryScreen(viewModel: MainViewModel) {
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
                subCategories.data?.let { GridListView(it) }
            }
        }
    }
}