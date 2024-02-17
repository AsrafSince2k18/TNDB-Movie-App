package com.example.movieshow.ui.presentance.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Shimmer() {
        Box(
            modifier = Modifier
                .height(200.dp)
                .width(130.dp),
            contentAlignment = Alignment.Center
        ) {
            Card {
                Box(modifier = Modifier
                    .fillMaxSize())
            }
        }

}