package com.infosys.presenters

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.util.fastForEachIndexed
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.infosys.R
import com.infosys.presenters.ui.main.MainMenuScreen
import com.infosys.presenters.viewmodel.MainViewModel
import com.infosys.presenters.ui.main.MainScreen
import com.infosys.presenters.ui.theme.FoodDeliveryDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val navigationItems = listOf(
        NavigationItem("Home", R.drawable.ic_remove,R.drawable.ic_add),
        NavigationItem("Menu", R.drawable.arrow_up,R.drawable.arrow_down),
        NavigationItem("Setting", R.drawable.ic_arrow_forward,R.drawable.ic_search),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodDeliveryDemoTheme {
                val index = remember { mutableIntStateOf(0) }
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = navigationItems[0].name) {
                    composable(navigationItems[0].name) {
                        MainScreen(viewModel)
                    }
                    composable(navigationItems[1].name) {
                        MainMenuScreen(viewModel)
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            navigationItems.fastForEachIndexed { i, navigationItem ->
                                NavigationBarItem(
                                    selected = index.value == i,
                                    onClick = {
                                        index.value = i
                                        navController.navigate(navigationItem.name)
                                    },
                                    icon = {
                                        BadgedBox(badge = {}) {
                                            Icon(
                                                painterResource(
                                                    if (index.value == i) {
                                                        navigationItem.focusedIcon
                                                    } else {
                                                        navigationItem.unfocusedIcon
                                                    }
                                                ), null
                                            )
                                        }
                                    },
                                    alwaysShowLabel = false
                                )
                            }
                        }
                    }
                ) { _ ->
                    viewModel.getAllCategories()
                }
            }
        }
    }

    companion object {
        fun startActivity(activity: Activity) {
            Intent(activity, MainActivity::class.java).apply {
                activity.startActivity(this)
                activity.finishAffinity()
            }
        }
    }
}

data class NavigationItem(
    val name: String,
    @DrawableRes
    val focusedIcon: Int,
    @DrawableRes
    val unfocusedIcon: Int
)