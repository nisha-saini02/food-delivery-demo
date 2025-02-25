package com.infosys.presenters.ui.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.infosys.R
import com.infosys.presenters.viewmodel.MainViewModel
import com.infosys.data.model.category.Category
import com.infosys.data.model.category.sub_Category.SubCategory
import com.infosys.presenters.ui.Image
import com.infosys.presenters.ui.LoadImage
import com.infosys.presenters.ui.Spacer
import com.infosys.presenters.ui.TextHeadlineLarge
import com.infosys.presenters.ui.TextTitleSmall
import com.infosys.presenters.ui.roundShapeCorner
import com.infosys.presenters.ui.theme.Orange
import com.infosys.presenters.ui.theme.Yellow
import com.infosys.presenters.ui.theme.White
import com.infosys.presenters.ui.listViews.GridListView

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val data = viewModel.categories.collectAsState().value
    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .background(Orange, roundShapeCorner(0,0,30,30))
                .weight(0.1f),
            contentAlignment = Alignment.Center
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                TextHeadlineLarge("All Categories", color = White)
            }
        }

        Box (
            modifier = Modifier
                .fillMaxSize()
                .weight(0.8f)
                .padding(vertical = 16.dp, horizontal = 16.dp)
        ) {
            data.data?.let {
                AnimateExpandableList(data.data, viewModel)
            }
        }
    }
}

@Composable
fun AnimateExpandableList(citiesResponse: List<Category>, viewModel: MainViewModel) {
    val data = viewModel.subcategories.collectAsState().value
    val expandedStates = remember { mutableStateListOf(*BooleanArray(citiesResponse.size) { false }.toTypedArray()) }
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        state = listState
    ) {

        itemsIndexed(citiesResponse.toList()) {index: Int, item: Category ->
            ExpandableListItem(
                item = item,
                isExpanded = expandedStates[index],
                onExpandedChange = {
                    expandedStates[index] = it
                    if (expandedStates[index]) {
                        viewModel.getSubCategories(item.strCategory.toString())
                    }
                                   },
                data.data
            )
        }
    }
}

@Composable
fun ExpandableListItem(
    item: Category,
    isExpanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    data: List<SubCategory>?
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, shape = roundShapeCorner())
            .background(
                color = Yellow,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable (interactionSource = interactionSource, indication = null) {
                onExpandedChange(!isExpanded)
            }
            .padding(16.dp)
    ) {
        Row {
            LoadImage(item.strCategoryThumb.toString())

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.8f)
                    .padding(horizontal = 5.dp),
                horizontalAlignment = Alignment.Start
            ) {
                TextTitleSmall(
                    text = item.strCategory.toString(),
                    textAlign = TextAlign.Start
                )
                Spacer(5)
                TextTitleSmall(
                    text = item.strCategoryDescription.toString(),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 2,
                    textAlign = TextAlign.Start
                )
            }

            Image(
                id = if (isExpanded) R.drawable.arrow_up else R.drawable.arrow_down,
                modifier = Modifier.wrapContentSize().weight(0.1f)
            )
        }

        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                data?.let { GridListView(it, 2) }
            }
        }
    }
}