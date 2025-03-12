package com.infosys.presentation.ui.screens.shimmer_effect

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import com.infosys.presentation.ui.screens.navigation.NavigationRoute
import com.infosys.theme.Orange
import com.infosys.theme.Yellow

@Composable
fun ShimmerNavigator(route: NavigationRoute) {
    // Creating infinite transition
    // for each composable
    val transition = rememberInfiniteTransition(label = "")
    val translateAnim by transition.animateFloat(

        // Specify animation positions, initial
        // Values of means it starts from 0 position
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(

            // Tween Animates between values
            // over specified [durationMillis]
            tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        ), label = ""
    )

    // Using linear gradient for animation
    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )

    when(route) {
        NavigationRoute.HOME -> {
            MainScreenShimmer(brush = brush)
        }
        NavigationRoute.MENU -> {
            MainMenuShimmer(brush = brush)
        }
        NavigationRoute.MENU_HORIZONTAL -> {
            MainMenuHorizontalShimmer(brush = brush)
        }
        NavigationRoute.CART -> {
            CartShimmer(brush)
        }
        NavigationRoute.SUBCATEGORY -> {
            SubCategoryShimmer(brush)
        }
        NavigationRoute.ORDER -> {
            OrderShimmer(brush)
        }

        else -> {}
    }
}

val ShimmerColorShades = listOf(
    Yellow.copy(0.9f),
    Yellow.copy(0.5f),
    Orange.copy(0.9f)
)