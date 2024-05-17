package com.learningwithmanos.uniexercise.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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


@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
@Preview
fun MainUi() {
    val navHost = rememberNavController()
    val currentScreen =
        rememberSaveable { mutableStateOf(AvailableScreens.HeroScreen) } //TODO Properly with flow or other wrapper
    val searchText = rememberSaveable { mutableStateOf("") }
    val isSearching = remember { mutableStateOf(false) }


    LaunchedEffect(currentScreen.value) {
        navHost.navigate(currentScreen.value.name) {
            launchSingleTop = true
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AnimatedVisibility(
                visible = currentScreen.value != AvailableScreens.SettingsScreen,
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
                            navHost.navigate(AvailableScreens.HeroScreen.name) {
                                launchSingleTop = true
                            }
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
                            navHost.navigate(AvailableScreens.QueryScreen.name) {
                                launchSingleTop = true
                            }
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
                            navHost.navigate(AvailableScreens.QuizScreen.name) {
                                launchSingleTop = true
                            }
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
                targetState = currentScreen.value,
                transitionSpec = {
                    slideInVertically(animationSpec = tween(400)) togetherWith slideOutVertically(
                        animationSpec = tween(400)
                    )
                }) { targetState ->
                when (targetState) {
                    AvailableScreens.QueryScreen -> {
                        SearchBar(
                            enabled = currentScreen.value == AvailableScreens.QueryScreen,
                            query = searchText.value,//text showed on SearchBar
                            active = isSearching.value,
                            onActiveChange = {},
                            onQueryChange = { searchText.value = it },
                            onSearch = {},
                            trailingIcon = {
                                Row {
                                    AnimatedVisibility(
                                        visible = currentScreen.value != AvailableScreens.SettingsScreen,
                                        enter = scaleIn(),
                                        exit = scaleOut()
                                    ) {
                                        IconButton(onClick = {
                                            navHost.navigate(AvailableScreens.SettingsScreen.name) {
                                                launchSingleTop = true
                                            }
                                        }) {
                                            Icon(Icons.Default.Settings, "")
                                        }
                                    }
                                    AnimatedVisibility(visible = currentScreen.value == AvailableScreens.QueryScreen) {
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

                    else -> {
                        TopAppBar(
                            title = { Text(text = currentScreen.value.name) },
                            modifier = Modifier.animateContentSize(),
                            actions = {
                                IconButton(
                                    onClick = {
                                        navHost.navigate(AvailableScreens.SettingsScreen.name) {
                                            launchSingleTop = true
                                        }
                                    })
                                {
                                    Icon(Icons.Default.Settings, "")
                                }

                            }
                        )


                    }

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
                currentScreen.value = AvailableScreens.HeroScreen
                HeroesScreen()
            }
            composable(AvailableScreens.QueryScreen.name) {
                currentScreen.value = AvailableScreens.QueryScreen
                QueryScreen(searchText)
            }
            composable(AvailableScreens.QuizScreen.name) {
                currentScreen.value = AvailableScreens.QuizScreen
                QuizScreen()
            }
            //TODO These anim feel weird/buggy
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
                currentScreen.value = AvailableScreens.SettingsScreen

                SettingsScreen({ navHost.popBackStack() })
            }

        }

    }
}
