package com.learningwithmanos.uniexercise.heroes.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun MainUi(){
    val navHost = rememberNavController()

    Scaffold(
        modifier= Modifier.fillMaxSize(),
        bottomBar = { BottomAppBar {
            NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = { Icon(Icons.Default.AccountBox,"") })
            NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = { Icon(Icons.Default.Close,"") })
            NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = { Icon(Icons.Default.Call,"") })
        }
                    },
        topBar = { CenterAlignedTopAppBar(title = { Text(text = "Hello") }, actions = {Icon(Icons.Default.Settings,"")}) }){ paddingValues ->

        NavHost(navController = navHost, startDestination = AvailableScreens.HeroScreen.name, modifier = Modifier.padding(paddingValues)){
            composable(AvailableScreens.HeroScreen.name){
                HeroesScreen()
            }
            composable(AvailableScreens.SomeOther.name){

            }
            composable(AvailableScreens.Todo.name){

            }

        }

    }



}