package com.learningwithmanos.uniexercise.ui.HeroDetailesScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.ui.HeroesViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HeroDetailsScreen(
    hero: Hero,
    viewModel: HeroDetailsViewModel = hiltViewModel()
) {
    Row {
        GlideImage(model = hero.imageUrl, contentDescription = "")
        Text(text = hero.name)
    }
}

@Preview
@Composable
fun HeroPrev(){
    HeroDetailsScreen(hero = Hero(1,"Spider-Man",5,"",""))
}