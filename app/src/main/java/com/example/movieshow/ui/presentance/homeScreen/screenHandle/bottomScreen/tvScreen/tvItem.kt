package com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.tvScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieshow.R
import com.example.movieshow.root.domain.model.Movie
import com.example.movieshow.root.domain.model.Tv
import com.example.movieshow.ui.presentance.component.RatingBar
import com.example.movieshow.ui.presentance.utils.Utils

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TvItem(tv: Tv, onClick: () -> Unit) {

    val context = LocalContext.current

    val url = Utils.IMAGE_BASE_URL_original + tv.poster_path

    Column(
        modifier = Modifier
            .width(180.dp)
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
            modifier = Modifier
                .clickable { onClick() },
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 4.dp, top = 4.dp, end = 4.dp, bottom = 6.dp)
            ) {
                AsyncImage(
                    model = ImageRequest
                        .Builder(context)
                        .crossfade(true)
                        .crossfade(400)
                        .data(url)
                        .build(),
                    error = painterResource(id = R.drawable.place_holder),
                    placeholder = painterResource(id = R.drawable.place_holder),
                    contentDescription = "imageUrl",
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
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
                tv.original_name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        maxLines = 1,
                        modifier = Modifier
                            .padding(start = 2.dp, end = 2.dp)
                            .basicMarquee()
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(start = 2.dp, end = 2.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    RatingBar(
                        starsModifier = Modifier.size(18.dp),
                        rating = tv.vote_average?.div(2) ?: 0.0
                    )
                    Spacer(modifier = Modifier
                        .width(2.dp))
                    Text(
                        text = tv.vote_average.toString().take(3),
                        color = Color.LightGray,
                        fontSize = 12.sp
                    )

                }

            }

        }
    }

}