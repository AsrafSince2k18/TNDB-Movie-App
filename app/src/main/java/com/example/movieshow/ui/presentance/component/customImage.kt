package com.example.movieshow.ui.presentance.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieshow.R

@Composable
fun CustomImage(
    url : String,
    modifier : Modifier = Modifier
) {

    val context = LocalContext.current

    AsyncImage(
        model =ImageRequest
            .Builder(context)
            .crossfade(true)
            .crossfade(400)
            .data(url)
            .build(),
        error = painterResource(id = R.drawable.place_holder),
        placeholder = painterResource(id = R.drawable.place_holder),
        contentDescription ="imageUrl",
        contentScale = ContentScale.FillBounds,
        alignment = Alignment.Center,
        modifier = modifier
    )

}