package com.learningwithmanos.uniexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.learningwithmanos.uniexercise.heroes.ui.HeroScreen.HeroesScreen
import com.learningwithmanos.uniexercise.heroes.ui.MainUi
import com.learningwithmanos.uniexercise.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppPreferences.setup(applicationContext)
        AppPreferences.apikey = "0cf69d45e2482a87f2a9af138efba603"
        AppPreferences.privatekey = "8aa649a8b299924f9428f6db08189950b7bfd728"

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainUi()
                }
            }
        }
    }
}