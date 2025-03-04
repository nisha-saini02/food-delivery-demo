package com.infosys.presentation.ui.screens.navigation

import androidx.annotation.DrawableRes

data class NavigationItem(
    val name: String,
    @DrawableRes
    val focusedIcon: Int,
    @DrawableRes
    val unfocusedIcon: Int
)