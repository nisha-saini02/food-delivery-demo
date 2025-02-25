package com.infosys.presenters.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.infosys.R
import com.infosys.presenters.ui.EditTextBodyMedium
import com.infosys.presenters.ui.Image
import com.infosys.presenters.ui.Spacer
import com.infosys.presenters.ui.TextHeadlineSmall
import com.infosys.presenters.ui.roundShapeCorner
import com.infosys.presenters.ui.theme.Orange
import com.infosys.presenters.ui.theme.White
import com.infosys.presenters.ui.listViews.HorizontalCategoriesListView
import com.infosys.presenters.ui.listViews.MainMenuListView
import com.infosys.presenters.viewmodel.MainViewModel

@Composable
fun MainMenuScreen(viewModel: MainViewModel) {
//    viewModel.getAllCategories()
//    viewModel.getMenuList()
    val search = remember { mutableStateOf("") }
    val categories = viewModel.categories.collectAsState().value
    val meals = viewModel.meals.collectAsState().value

    Column(modifier = Modifier.fillMaxSize().background(White)) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Orange, roundShapeCorner(0, 0, 30, 30))
                .weight(0.2f),
            contentAlignment = Alignment.CenterStart
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
                ) {

                Image(
                    id = R.drawable.ic_search,
                    modifier = Modifier.size(25.dp)
                )

                EditTextBodyMedium (search.value, hint = "Search your favorite dish") {
                    search.value = it
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(1.5f),
        ) {
            Column {

                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextHeadlineSmall(
                        "All Categories",
                        modifier = Modifier.fillMaxWidth().weight(0.9f),
                        textAlign = TextAlign.Start
                    )
                    Image(
                        R.drawable.ic_arrow_forward,
                        modifier = Modifier.wrapContentSize().weight(0.1f)
                    )
                }

                Spacer()

                categories.data?.let {
                    HorizontalCategoriesListView(it)
                }

                Spacer()

                TextHeadlineSmall("All Items")

                Spacer()

                meals.data?.let {
                    MainMenuListView(it)
                }
            }
        }
    }
}