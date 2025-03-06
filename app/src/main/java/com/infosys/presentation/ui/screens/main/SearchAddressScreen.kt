package com.infosys.presentation.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.infosys.R
import com.infosys.presentation.ui.screens.utility.EditTextBodyMedium
import com.infosys.presentation.ui.screens.utility.HorizontalLine
import com.infosys.presentation.ui.screens.utility.Image
import com.infosys.presentation.ui.screens.utility.Spacer
import com.infosys.presentation.ui.screens.utility.TextLabelMedium
import com.infosys.presentation.ui.screens.utility.TextLabelSmall
import com.infosys.presentation.ui.screens.navigation.NavigationRoute
import com.infosys.presentation.ui.screens.utility.roundShapeCorner
import com.infosys.theme.Black
import com.infosys.theme.Gray
import com.infosys.theme.Orange
import com.infosys.theme.White

@Composable
fun SearchAddressScreen(navHostController: NavHostController) {

    val search = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Orange, roundShapeCorner(0, 0, 30, 30))
                .weight(0.25f),
            contentAlignment = Alignment.CenterStart
        ) {
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            ) {
                Image(
                    id = R.drawable.ic_cancel,
                    modifier = Modifier.size(25.dp)
                ) {
                    navHostController.popBackStack()
                }

                Row (verticalAlignment = Alignment.CenterVertically) {
                    Image(R.drawable.ic_start_location, Modifier.wrapContentSize())
                    TextLabelMedium ("Subway, Rayya", modifier = Modifier.fillMaxWidth().padding(16.dp), textAlign = TextAlign.Start)
                }

                HorizontalLine()

                Row (verticalAlignment = Alignment.CenterVertically) {
                    Image(R.drawable.ic_destination, Modifier.wrapContentSize())
                    EditTextBodyMedium(
                        search.value,
                        hint = "Choose your location",
                        textColor = Black,
                        hintColor = Black,
                    ) {
                        search.value = it
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 110.dp, start = 16.dp, end = 16.dp, top = 16.dp)
                .weight(0.75f),
        ) {

            Column {
                search.value.let {
                    LocationItems(
                        listOfCities.filter { it.contains(search.value, ignoreCase = true) }
                    ) {
                        navHostController.navigate(NavigationRoute.ORDER.route)
                    }
                }
            }
        }
    }
}

@Composable
fun LocationItems(items: List<String>, clickEvent: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 500.dp)
    ) {
        LazyVerticalGrid (
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(5.dp)
        ) {
            items(items) { item ->
                Column (
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(vertical = 8.dp)
                        .clickable { clickEvent(item) },
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row (
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(R.drawable.ic_location, Modifier.wrapContentSize())
                        Spacer(5)
                        TextLabelSmall(text = item)
                    }
                    Spacer(5)
                    HorizontalLine(color = Gray)
                }
            }
        }
    }
}


//Static list, needed to implement places api which requires credits in google map platform
val listOfCities = listOf(
    "Beas Town, Amritsar, India",
    "Baba Deep Singh Nagar, Amritsar, India",
    "Beas Market Area, Amritsar, India",
    "Guru Nanak Nagar, Amritsar, India",
    "New Beas Colony, Amritsar, India",
    "Sarai Naurangabad, Amritsar, India",
    "Ravi Nagar, Amritsar, India",
    "Beas Mandi, Amritsar, India",
    "Hardeep Nagar, Amritsar, India",
    "New Model Town, Amritsar, India",
    "Guru Nanak Enclave, Amritsar, India",
    "Sultanwind Road Area, Amritsar, India",
    "Beas Railway Station Area, Amritsar, India",
    "Civil Lines, Amritsar, India",
    "Gurunanak Pura, Amritsar, India",
    "Basti Shankar, Amritsar, India",
    "Beas Kalan, Amritsar, India",
    "Chowk Beas, Amritsar, India",
    "Harmandir Nagar, Amritsar, India",
    "Prakash Colony, Amritsar, India",
    "Shivpuri, Amritsar, India",
    "Amar Nagar, Amritsar, India",
    "Ajaib Nagar, Amritsar, India",
    "Naiwala, Amritsar, India",
    "Rajinder Nagar, Amritsar, India",
    "Surjit Nagar, Amritsar, India",
    "Ranjit Nagar, Amritsar, India",
    "Saraswati Nagar, Amritsar, India",
    "Vishwakarma Nagar, Amritsar, India",
    "Baba Bakala Road Area, Amritsar, India"
)