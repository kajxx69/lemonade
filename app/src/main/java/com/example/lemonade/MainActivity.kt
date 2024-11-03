package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    Surface {
        Column {
            // Barre d'en-tête
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFEB3B)) // Couleur jaune
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Lemonade",
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }
            LemonadeContent()
        }
    }
}

@Composable
fun LemonadeContent() {
    // Gestion des étapes
    var step by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    val maxSqueezeCount = (2..4).random()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Texte de chaque étape

        // Image cliquable correspondant à chaque étape
        Image(
            painter = painterResource(
                id = when (step) {
                    1 -> R.drawable.lemon_tree // citronnier
                    2 -> R.drawable.lemon_squeeze // citron
                    3 -> R.drawable.lemon_drink // verre de citronnade
                    else -> R.drawable.lemon_restart // verre vide
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .clickable {
                    when (step) {
                        1 -> {
                            step = 2
                            squeezeCount = 0
                        }
                        2 -> {
                            if (squeezeCount < maxSqueezeCount) {
                                squeezeCount++
                            } else {
                                step = 3
                            }
                        }
                        3 -> step = 4
                        else -> step = 1
                    }
                }
        )
        Text(
            text = when (step) {
                1 -> stringResource(R.string.tap_lemon_tree)
                2 -> stringResource(R.string.tap_lemon)
                3 -> stringResource(R.string.tap_lemonade)
                else -> stringResource(R.string.tap_empty_glass)
            },
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}