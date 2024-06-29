package com.learningwithmanos.uniexercise.ui.HeroScreen.HeroDetailsDialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import androidx.constraintlayout.widget.ConstraintLayout
import coil.compose.SubcomposeAsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.learningwithmanos.uniexercise.heroes.data.Hero

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HeroDetailsDialog(
    onDismissRequest: () -> Unit,
    hero: Hero
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            true,
            true,
            SecureFlagPolicy.Inherit,
            false,)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
                Row {
                    SubcomposeAsyncImage(
                        model = hero.imageUrl,
                        loading = {
                            CircularProgressIndicator()
                        },
                        error = { Text(text = "Something went wrong") },
                        contentDescription = null,
                        modifier = Modifier
                            .size(180.dp, 180.dp)
                            .padding(10.dp),
                        contentScale = ContentScale.Crop)
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(0.5f)
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalArrangement = Arrangement.Bottom,) {
                        Text(text = hero.name, style = MaterialTheme.typography.headlineMedium)
                        Text(text = "Number of Comics: ${hero.availableComics}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            HorizontalDivider()
            Text(hero.description.run {
                ifBlank {
                    "This hero has no description"
                }
            },
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.bodyLarge)

        }
    }
}