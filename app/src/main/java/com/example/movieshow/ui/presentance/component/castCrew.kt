package com.example.movieshow.ui.presentance.component

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieshow.R

@Composable
fun CastCrewImage(url : String,
                  modifier : Modifier = Modifier) {

    val context = LocalContext.current

    AsyncImage(
        model = ImageRequest
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
            .clip(CircleShape)
    )


}