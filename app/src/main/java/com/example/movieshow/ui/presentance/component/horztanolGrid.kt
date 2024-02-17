package com.example.movieshow.ui.presentance.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HorizonatalGrid() {

    Box(
        modifier = Modifier
            .width(180.dp)
            .height(300.dp)
            .padding(4.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 8.dp,
                    bottomEnd = 12.dp,
                    bottomStart = 12.dp
                )
            )

    ) {

        Card(
            modifier = Modifier,
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 4.dp, top = 4.dp, end = 4.dp, bottom = 6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                topStart = 8.dp,
                                topEnd = 8.dp,
                                bottomEnd = 12.dp,
                                bottomStart = 12.dp
                            )
                        )
                )
            }

            Box(
                modifier = Modifier
                    .padding(start = 2.dp, end = 2.dp)
                    .height(220.dp)
                    .fillMaxWidth()
            ) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    )


            }

        }

    }
}


@Preview
@Composable
fun AA() {
    HorizonatalGrid()
}
