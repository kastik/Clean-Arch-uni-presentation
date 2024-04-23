package com.learningwithmanos.uniexercise.heroes.ui.QueryScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.ui.HeroCard


@Composable
fun QueryScreen(data: Hero){
    HeroCard(data = data,true)
}


@Preview
@Composable
fun prev(){
    QueryScreen(Hero(1,"",1,""))
}