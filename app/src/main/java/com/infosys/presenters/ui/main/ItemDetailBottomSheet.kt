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
import androidx.compose.foundation.layout.wrapContentWidth
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
    itemName: String,
    itemInstruction: String,
    itemThumbnail: String,
    itemPrice: String = "$15",
    count: MutableIntState = remember { mutableIntStateOf(1) },
    isBottomSheetVisible: Boolean,
    sheetState: SheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    ),
    onDismiss: () -> Unit
) {

        if (isBottomSheetVisible) {

            ModalBottomSheet(
                onDismissRequest = onDismiss,
                sheetState = sheetState,
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onSurface,
                shape = RectangleShape,
                dragHandle = null,
                scrimColor = Color.Black.copy(alpha = .5f),
                windowInsets = WindowInsets(0, 0, 0, 0)
            ) {

                Box (modifier = Modifier.fillMaxWidth().padding(top = 50.dp).background(White, roundShapeCorner(20, 20, 0, 0))) {
                    Column (
                        modifier = Modifier.fillMaxWidth().padding(16.dp).verticalScroll(
                            rememberScrollState()
                        ),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TextTitleLarge(itemName) {}

                        Spacer()

                        LoadImage(itemThumbnail, Modifier.size(150.dp))

                        Spacer()

                        TextTitleSmall("Instructions :=\n$itemInstruction", maxLines = Int.MAX_VALUE, textAlign = TextAlign.Start)

                        Spacer()

                        Row {
                            Box(Modifier.fillMaxWidth().weight(0.3f), Alignment.CenterStart) {
                                TextTitleMedium(itemPrice, textAlign = TextAlign.Start)
                            }
                            Box(Modifier.fillMaxWidth().weight(0.7f), Alignment.CenterEnd) {
                                Row {
                                    Image(R.drawable.ic_remove, modifier = Modifier.wrapContentSize()) {
                                        if (count.value > 1) {
                                            count.value -= 1
                                        }
                                    }
                                    Spacer(8)
                                    TextLabelLarge(text = count.value.toString())
                                    Spacer(8)
                                    Image(R.drawable.ic_add, modifier = Modifier.wrapContentSize()) {
                                        count.value += 1
                                    }
                                }
                            }
                        }

                        Spacer()

                        ButtonCr(text = "Add Item To Cart") {

                        }
                    }
                }

            }

        }

}
