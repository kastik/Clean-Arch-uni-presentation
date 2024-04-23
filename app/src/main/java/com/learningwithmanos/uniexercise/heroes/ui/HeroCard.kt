package com.learningwithmanos.uniexercise.heroes.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.Placeholder
import com.bumptech.glide.integration.compose.placeholder
import com.learningwithmanos.uniexercise.heroes.data.Hero


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HeroCard(data: Hero){ //TODO Change to hero data
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
    ){
        Row(Modifier.fillMaxWidth()) {
            Column(Modifier.fillMaxHeight(1f).fillMaxWidth(0.2f)) {
                GlideImage(
                    model = data.imageUrl,
                    contentDescription = "getString(R.id.picture_of_cat)",
                    loading = placeholder { Icon(Icons.AutoMirrored.Filled.ArrowBack, "") }
                )
            }
            Column(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f)) {
                Text(text = data.name)
                Text(text = data.availableComics.toString())
            }
            Column(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()) {
                Icon(Icons.Default.PlayArrow, contentDescription = "")
            }

        }



    }

        //Onoma
        //Description
        //thumbnail

}


@Composable
@Preview
fun HeroPreview(){
    HeroCard(Hero(5,"",5,""))
}