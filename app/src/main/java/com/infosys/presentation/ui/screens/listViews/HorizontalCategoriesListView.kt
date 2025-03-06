package com.infosys.presentation.ui.screens.listViews

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.infosys.data.model.category.Category
import com.infosys.presentation.ui.screens.utility.LoadImage
import com.infosys.presentation.ui.screens.utility.Spacer
import com.infosys.presentation.ui.screens.utility.TextLabelSmall
import com.infosys.presentation.ui.screens.utility.roundShapeCorner
import com.infosys.theme.Yellow

@Composable
fun HorizontalCategoriesListView(items: List<Category>, spanCount: Int = 1, clickEvent: (Category) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 140.dp)
    ) {
        LazyHorizontalGrid (
            rows = GridCells.Fixed(spanCount),
            contentPadding = PaddingValues(5.dp)
        ) {
            items(items) { item ->
                Column(
                    modifier = Modifier
                        .width(110.dp)
                        .height(130.dp)
                        .padding(8.dp)
                        .background(Yellow, roundShapeCorner(8))
                        .clickable { clickEvent(item) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    LoadImage(item.strCategoryThumb.toString())
                    Spacer(5)
                    TextLabelSmall(text = item.strCategory.toString())
                }
            }
        }
    }
}