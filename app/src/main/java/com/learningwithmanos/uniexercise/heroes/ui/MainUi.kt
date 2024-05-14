package com.learningwithmanos.uniexercise.heroes.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.learningwithmanos.uniexercise.heroes.ui.HeroScreen.HeroesScreen
import com.learningwithmanos.uniexercise.heroes.ui.QueryScreen.QueryScreen
import com.learningwithmanos.uniexercise.heroes.ui.QuizScreen.QuizScreen
import com.learningwithmanos.uniexercise.heroes.ui.SettingsScreen.SettingsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun MainUi(){
    val navHost = rememberNavController()
    val openTab = remember { mutableStateOf(AvailableScreens.HeroScreen) } //TODO Properly with flow or other wrapper
    val showStuff = remember { derivedStateOf { openTab.value == AvailableScreens.SettingsScreen } }
    Scaffold(
        modifier= Modifier.fillMaxSize(),

        bottomBar = {
            AnimatedVisibility(
                visible = openTab.value!=AvailableScreens.SettingsScreen,
                enter= expandVertically(),
                exit=  shrinkVertically()
            ) {
                BottomAppBar {
                    NavigationBarItem(
                        selected = false,
                        onClick = { navHost.navigate(AvailableScreens.HeroScreen.name) },
                        icon = {
                            Column {
                                Icon(Icons.Default.AccountBox, "")
                                Text(text = "Heros")
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


                    }
        ,
        topBar = { CenterAlignedTopAppBar(title = { Text(text = "Hello") }, actions =
        {
            AnimatedVisibility(
                visible = !showStuff.value,
                enter = scaleIn(),
                exit = scaleOut()
                ) {
                IconButton(onClick = {navHost.navigate(AvailableScreens.SettingsScreen.name) }) {
                    Icon(Icons.Default.Settings,"")
                }
            }
        }) }){ paddingValues ->

        NavHost(navController = navHost, startDestination = AvailableScreens.HeroScreen.name, modifier = Modifier.padding(paddingValues)){
            composable(AvailableScreens.HeroScreen.name){
                openTab.value = AvailableScreens.HeroScreen
                HeroesScreen()
            }
            composable(AvailableScreens.QueryScreen.name){
                openTab.value = AvailableScreens.QueryScreen
                QueryScreen()
            }
            composable(AvailableScreens.QuizScreen.name){
                openTab.value = AvailableScreens.QuizScreen
                QuizScreen()
            }
            composable(AvailableScreens.SettingsScreen.name){
                openTab.value = AvailableScreens.SettingsScreen
                SettingsScreen()
            }

        }

    }



}