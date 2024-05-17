package com.learningwithmanos.uniexercise.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.learningwithmanos.uniexercise.ui.HeroScreen.HeroesScreen
import com.learningwithmanos.uniexercise.ui.QueryScreen.QueryScreen
import com.learningwithmanos.uniexercise.ui.QuizScreen.QuizScreen
import com.learningwithmanos.uniexercise.ui.SettingsScreen.SettingsScreen

enum class AvailableScreens {
    HeroScreen, QueryScreen, QuizScreen, SettingsScreen
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
@Preview
fun MainUi() {
    val navHost = rememberNavController()
    val searchText = rememberSaveable { mutableStateOf("") }
    val isSearching = remember { mutableStateOf(false) }
    val navigate = { screen:AvailableScreens -> navHost.navigate(screen.name){launchSingleTop = true} }
    val navBackStackEntry by navHost.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AnimatedVisibility(
                visible = currentRoute != AvailableScreens.SettingsScreen.name,
                enter = expandVertically(
                    tween(400)
                ),
                exit = shrinkVertically(
                    tween(400)
                )
            ) {
                BottomAppBar(
                    modifier = Modifier.animateContentSize()
                ) {
                    NavigationBarItem(
                        selected = false,
                        onClick = {
                            navigate(AvailableScreens.HeroScreen)
                        },
                        icon = {
                            Column {
                                Icon(Icons.Default.AccountBox, "")
                                Text(text = "Heroes")
                            }
                        })
                    NavigationBarItem(
                        selected = false,
                        onClick = {
                            navigate(AvailableScreens.QueryScreen)
                        },
                        icon = {
                            Column {
                                Icon(Icons.Default.Close, "")
                                Text(text = "Query")
                            }
                        })
                    NavigationBarItem(
                        selected = false,
                        onClick = {
                            navigate(AvailableScreens.QuizScreen)
                        },
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
            AnimatedContent(
                targetState = currentRoute,
                transitionSpec = {
                    slideInVertically(animationSpec = tween(400)) togetherWith slideOutVertically(
                        animationSpec = tween(400)
                    )
                }) { targetState ->
                if (targetState == AvailableScreens.QueryScreen.name) {
                        SearchBar(
                            enabled = currentRoute == AvailableScreens.QueryScreen.name,
                            query = searchText.value,//text showed on SearchBar
                            active = isSearching.value,
                            onActiveChange = {},
                            onQueryChange = { searchText.value = it },
                            onSearch = {},
                            trailingIcon = {
                                Row {
                                    IconButton(onClick = {
                                        navigate(AvailableScreens.SettingsScreen)
                                    }) {
                                        Icon(Icons.Default.Settings, "")
                                    }
                                    AnimatedVisibility(visible = currentRoute == AvailableScreens.QueryScreen.toString()) {
                                        IconButton(onClick = { /*TODO*/ }) {
                                            Icon(Icons.Default.Search, "")
                                        }
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ){}
                    }
                    else{
                        TopAppBar(
                            title = { Text(text = currentRoute.toString()) },
                            actions = {
                                IconButton(
                                    onClick = {
                                        navigate(AvailableScreens.SettingsScreen)
                                    })
                                {
                                    Icon(Icons.Default.Settings, "")
                                }

                            }
                        )
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
                AvailableScreens.HeroScreen.name,
            ) {
                HeroesScreen()
            }
            composable(AvailableScreens.QueryScreen.name) {
                QueryScreen(searchText)
            }
            composable(AvailableScreens.QuizScreen.name) {
                QuizScreen()
            }
            composable(
                AvailableScreens.SettingsScreen.name,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Up,
                        animationSpec = tween(800)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Down,
                        animationSpec = tween(800)
                    )
                },
            ) {
                SettingsScreen({ navHost.popBackStack() })
            }

        }
    }
}
