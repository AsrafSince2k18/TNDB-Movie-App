package com.example.movieshow.ui.presentance.homeScreen.screenHandle.extraDetailsTv

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayCircleFilled
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailTv.details.TvDetails
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailTv.smilar.TvSimilar
import com.example.movieshow.ui.presentance.component.CastCrewImage
import com.example.movieshow.ui.presentance.component.CustomImage
import com.example.movieshow.ui.presentance.component.MoviePoster
import com.example.movieshow.ui.presentance.component.RatingBar
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.extraDetailsTv.stateEvent.DetailsTvEvent
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.extraDetailsTv.stateEvent.DetailsTvState
import com.example.movieshow.ui.presentance.navGraph.screenRoot.BottomRoute
import com.example.movieshow.ui.presentance.utils.Utils
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.delay

@Composable
fun ExtraDetailsTv(
    navHostController: NavHostController,
    navBackStackEntry: NavBackStackEntry?,
    detailsState: DetailsTvState,
    event: (DetailsTvEvent) -> Unit
) {
    val context = LocalContext.current
    val getKey = navBackStackEntry?.arguments?.getInt("id") ?: 0
    if(getKey<=0){
        Toast.makeText(context, "Some thing went wrong", Toast.LENGTH_SHORT).show()
    }

    val localLifecycleOwner = LocalLifecycleOwner.current

    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = Unit) {
        event(DetailsTvEvent.CurrentId(currentId = getKey))
        delay(2000)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            if (detailsState.isLoading) {
                CircularProgressIndicator()
            } else {
                TvSectionOne(
                    tvDetails = detailsState.tvDetails,
                    detailsState = detailsState,
                    localLifecycleOwner = localLifecycleOwner,
                    event = event
                )

                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .padding(6.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        Box(
                            modifier = Modifier
                                .height(250.dp)
                                .width(150.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxSize(),
                                elevation = 4.dp,
                                shape = RoundedCornerShape(12.dp),
                                backgroundColor = MaterialTheme.colorScheme.surface
                            ) {
                                MoviePoster(
                                    url = Utils.IMAGE_BASE_URL_original + detailsState.tvDetails?.poster_path,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .align(Alignment.Center)
                                )

                            }

                        }

                        Spacer(
                            modifier = Modifier
                                .width(8.dp)
                        )

                        TvDetails(detailsState.tvDetails)

                    }

                    Spacer(
                        modifier = Modifier
                            .height(8.dp)
                    )

                    detailsState.tvDetails?.tagline?.let {
                        Text(
                            text = it,
                            fontStyle = FontStyle.Italic,
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .height(4.dp)
                    )
                    Text(
                        text = "Overview",
                        style = MaterialTheme.typography.titleLarge
                    )

                    detailsState.tvDetails?.overview?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }


                    //cast
                    Spacer(
                        modifier = Modifier
                            .height(4.dp)
                    )
                    Text(
                        text = "Cast & Crew",
                        style = MaterialTheme.typography.titleLarge
                    )
                    TvMovieCastAndCrew(detailsTvState = detailsState)

                    //similar
                    Spacer(
                        modifier = Modifier
                            .height(4.dp)
                    )
                    Text(
                        text = "Similar",
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleMedium
                    )

                    TvSimilarMovie(
                        detailsState.similar,
                        navHostController = navHostController
                    )

                }

            }
        }

    }


}

@Composable
fun TvSectionOne(
    tvDetails: TvDetails?,
    detailsState: DetailsTvState?,
    localLifecycleOwner: LifecycleOwner,
    event: (DetailsTvEvent) -> Unit
) {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        contentAlignment = Alignment.TopCenter
    ) {

        if (detailsState?.tvVideoClick == false) {
            CustomImage(url = Utils.IMAGE_BASE_URL_original + tvDetails?.backdrop_path,
                modifier = Modifier
                    .clickable {
                        event(DetailsTvEvent.VideoClick(true))
                    }
                    .align(Alignment.Center)
                    .fillMaxSize())
            Icon(
                imageVector = Icons.Outlined.PlayCircleFilled,
                contentDescription = "playVideo",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(62.dp),
                tint = Color.White.copy(alpha = 0.5f)
            )
        } else {
            if (detailsState?.tvVideoClick == true) {
                if (detailsState.tvVideoList.isEmpty()) {
                    CustomImage(url = Utils.IMAGE_BASE_URL_original + tvDetails?.backdrop_path,
                        modifier = Modifier
                            .clickable {
                                event(DetailsTvEvent.VideoClick(true))
                            }
                            .align(Alignment.Center)
                            .fillMaxSize())
                    Icon(
                        imageVector = Icons.Outlined.PlayCircleFilled,
                        contentDescription = "playVideo",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(62.dp),
                        tint = Color.White.copy(alpha = 0.5f)
                    )
                    Toast.makeText(context, "Nothing video", Toast.LENGTH_SHORT).show()
                } else {
                    TvYouTubePlay(
                        key = detailsState.tvVideoList[0].key,
                        lifecycleOwner = localLifecycleOwner
                    )
                }
            }
        }
    }

}

@Composable
fun TvYouTubePlay(
    key: String,
    lifecycleOwner: LifecycleOwner,
    modifier: Modifier = Modifier
) {


    AndroidView(factory = { context ->
        YouTubePlayerView(context).apply {
            lifecycleOwner.lifecycle.addObserver(this)
            addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(key, 0f)
                }

                override fun onError(
                    youTubePlayer: YouTubePlayer,
                    error: PlayerConstants.PlayerError
                ) {
                    Toast.makeText(context, error.name, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }, modifier = modifier)

}

@Composable
fun TvDetails(tvDetails: TvDetails?) {

    Column {
        tvDetails?.name?.let {
            Text(
                text = it,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge
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
                rating = tvDetails?.vote_average?.div(2) ?: 0.0
            )
            Spacer(
                modifier = Modifier
                    .width(2.dp)
            )
            Text(
                text = tvDetails?.vote_average.toString().take(3),
                color = Color.DarkGray,
                fontSize = 12.sp
            )

        }

        tvDetails?.episode_run_time?.let {
            Text(text = it.toString())
        }

        tvDetails?.original_language?.let {
            Text(
                text = "Language: $it",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall
            )
        }

        tvDetails?.number_of_seasons?.let {
            Text(
                text = "Number of season: $it",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall
            )
        }

        tvDetails?.number_of_episodes?.let {
            Text(
                text = "Number of episodes: $it",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall
            )
        }

        tvDetails?.status?.let {
            Text(
                text = "Status: $it",
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }

}


@Composable
fun TvMovieCastAndCrew(detailsTvState: DetailsTvState) {
    LazyRow(
        state = rememberLazyListState(),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        detailsTvState.castAndCrew?.cast?.let {
            items(it) { name ->
                CastCrewImage(
                    url = Utils.IMAGE_BASE_URL_original + name.profile_path,
                    modifier = Modifier
                        .size(110.dp)
                )
            }
        }
        detailsTvState.castAndCrew?.crew?.let {
            items(it) { name ->
                CastCrewImage(
                    url = Utils.IMAGE_BASE_URL_original + name.profile_path,
                    modifier = Modifier
                        .size(110.dp)
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TvSimilarMovie(tvSimilar: TvSimilar?,
                   navHostController: NavHostController
) {
    LazyRow(
        state = rememberLazyListState(),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        tvSimilar?.results?.let {
            items(it) { similar ->
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .width(130.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        onClick = {
                            navHostController.navigate("${BottomRoute.TvDetailsScreen.route}/${similar.id}")
                        },
                        modifier = Modifier
                            .fillMaxSize(),
                        elevation = 2.dp,
                        shape = RoundedCornerShape(18.dp),
                        backgroundColor = MaterialTheme.colorScheme.surface
                    ) {
                        MoviePoster(
                            url = "${Utils.IMAGE_BASE_URL_original}${similar.poster_path}",
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.Center)
                        )
                    }
                }
            }

        }

    }

}