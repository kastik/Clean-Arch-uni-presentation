package com.learningwithmanos.uniexercise.heroes.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.learningwithmanos.uniexercise.heroes.data.Hero
import com.learningwithmanos.uniexercise.heroes.ui.QueryScreen.QueryViewModel


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HeroCard(name: String, image: String, show: Boolean,onClick: () -> Unit ){ //TODO Change to hero data
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
            Column(
                Modifier
                    .fillMaxHeight(1f)
                    .fillMaxWidth(0.2f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GlideImage(
                    model = image,
                    contentDescription = "getString(R.id.picture_of_cat)",
                    loading = placeholder { Icon(Icons.Default.Create, "") }
                )
            }
            Column(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = name, style = MaterialTheme.typography.headlineSmall)
                //Text(text = data.availableComics.toString())
            }
            Column(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .clickable(enabled = true,onClick=onClick ),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.Download, contentDescription = "")
                    Text(text = "Download")
                }
                if(show){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.Search, contentDescription = "")
                    Text(text = "Query")
                }
                }
            }

        }



    }

}