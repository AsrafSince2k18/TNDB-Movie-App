package com.example.movieshow.ui.presentance.component.simmer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CardShimmer() {

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(250.dp)){
        Card {
            Box(modifier = Modifier
                .fillMaxSize())
        }
    }

}