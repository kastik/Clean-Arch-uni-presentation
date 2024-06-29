package com.learningwithmanos.uniexercise.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HeroCard(
    name: String,
    image: String,
    showQueryButton: Boolean,
    isInQueryScreen: Boolean,
    action1: () -> Unit, //In HeroScreen calls viewdetails,in query calls download
    query: () -> Unit){ //TODO Change to hero data
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .clickable { if(!isInQueryScreen){action1()}}
    ){
        Row(Modifier.fillMaxWidth()) {
            Column(
                Modifier
                    .fillMaxHeight(1f)
                    .fillMaxWidth(0.2f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SubcomposeAsyncImage(
                    model = image,
                    loading = {
                        CircularProgressIndicator()
                    },
                    error = { Text(text = "Something went wrong") },
                    contentDescription = null,
                    modifier = Modifier
                        .size(180.dp, 180.dp)
                        .padding(10.dp),
                    contentScale = ContentScale.Crop)
            }
            Column(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = name, style = MaterialTheme.typography.headlineSmall)
            }
            if (isInQueryScreen){
            Column(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable(onClick=action1 )) {
                    Icon(Icons.Default.Download, contentDescription = "")
                    Text(text = "Download")
                }
            }
                if(showQueryButton){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable(onClick=query )) {
                    Icon(Icons.Default.Search, contentDescription = "")
                    Text(text = "Query")
                }
                }
            }
        }
    }
}