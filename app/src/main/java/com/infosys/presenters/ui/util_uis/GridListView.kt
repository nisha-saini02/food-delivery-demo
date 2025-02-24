package com.infosys.presenters.ui.util_uis

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.infosys.data.model.category.sub_Category.SubCategory
import com.infosys.presenters.ui.LoadImage
import com.infosys.presenters.ui.TextHeadlineSmall

@Composable
fun GridListView(items: List<SubCategory>, spanCount: Int = 1) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 300.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(spanCount),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(items) { item ->
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LoadImage(
                        item.strMealThumb.toString(),
                        modifier = Modifier
                            .size(100.dp)
                            .padding(bottom = 8.dp)
                    )
                    TextHeadlineSmall(text = item.strMeal.toString())
                }
            }
        }
    }
}