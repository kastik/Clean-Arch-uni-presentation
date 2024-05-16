package com.learningwithmanos.uniexercise.ui

import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.learningwithmanos.uniexercise.ui.HeroScreen.HeroesScreen
import com.learningwithmanos.uniexercise.ui.QueryScreen.QueryScreen
import com.learningwithmanos.uniexercise.ui.QuizScreen.QuizScreen
import com.learningwithmanos.uniexercise.ui.SettingsScreen.SettingsScreen

enum class AvailableScreens {
    HeroScreen, QueryScreen, QuizScreen, SettingsScreen
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun MainUi() {
    val navHost = rememberNavController()
    val openTab =
        remember { mutableStateOf(AvailableScreens.HeroScreen) } //TODO Properly with flow or other wrapper
    val searchText = remember { mutableStateOf("") }
    val isSearchingon = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AnimatedVisibility(
                visible = openTab.value != AvailableScreens.SettingsScreen,
                enter = expandVertically(
                    tween(400)
                ),
                exit = shrinkVertically(
                    tween(400)
                )
            ) {
                BottomAppBar {
                    NavigationBarItem(
                        selected = false,
                        onClick = { navHost.navigate(AvailableScreens.HeroScreen.name) },
                        icon = {
                            Column {
                                Icon(Icons.Default.AccountBox, "")
                                Text(text = "Heroes")
                            }
                        })
                    NavigationBarItem(
                        selected = false,
                        onClick = { navHost.navigate(AvailableScreens.QueryScreen.name) },
                        icon = {
                            Column {
                                Icon(Icons.Default.Close, "")
                                Text(text = "Query")
                            }
                        })
                    NavigationBarItem(
                        selected = false,
                        onClick = { navHost.navigate(AvailableScreens.QuizScreen.name) },
                        icon = {
                            Column {
                                Icon(Icons.Default.Call, "")
                                Text(text = "Quiz")
                            }
                        })
                }
            }
        },
        topBar = {
            AnimatedVisibility(
                visible = openTab.value != AvailableScreens.SettingsScreen,
                enter = slideInVertically(
                    animationSpec = tween(400)
                ),
                exit = slideOutVertically(
                    animationSpec = tween(400)
                )
            ) {
                SearchBar(
                    enabled = false,
                    query = searchText.value ?: "Search",//text showed on SearchBar
                    active = isSearchingon.value,
                    onActiveChange = {},
                    onQueryChange = { searchText.value = it },
                    onSearch = {},
                    trailingIcon = {
                        Row {
                            AnimatedVisibility(
                                visible = openTab.value != AvailableScreens.SettingsScreen,
                                enter = scaleIn(),
                                exit = scaleOut()
                            ) {
                                IconButton(onClick = { navHost.navigate(AvailableScreens.SettingsScreen.name) }) {
                                    Icon(Icons.Default.Settings, "")
                                }
                            }
                            AnimatedVisibility(visible = openTab.value == AvailableScreens.QueryScreen) {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(Icons.Default.Search, "")
                                }
                            }

                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                }
            }

        })
    { paddingValues ->
        NavHost(
            navController = navHost,
            startDestination = AvailableScreens.HeroScreen.name,
            modifier = Modifier.padding(paddingValues),
        ) {
            composable(
                AvailableScreens.HeroScreen.name,) {
                openTab.value = AvailableScreens.HeroScreen
                HeroesScreen()
            }
            composable(AvailableScreens.QueryScreen.name) {
                openTab.value = AvailableScreens.QueryScreen
                QueryScreen(searchText)
            }
            composable(AvailableScreens.QuizScreen.name) {
                openTab.value = AvailableScreens.QuizScreen
                QuizScreen()
            }
            //TODO These anim feel weird/buggy
            composable(
                AvailableScreens.SettingsScreen.name,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Up,
                        animationSpec = tween(800)
                    )},
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Down,
                        animationSpec = tween(800)
                    )},
                ) {
                openTab.value = AvailableScreens.SettingsScreen

                SettingsScreen({navHost.popBackStack()})
            }

        }

    }
}


/*




 */
