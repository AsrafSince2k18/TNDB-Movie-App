package com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.homeScreen

import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieshow.R
import com.example.movieshow.root.domain.model.Movie
import com.example.movieshow.ui.presentance.component.CustomImage
import com.example.movieshow.ui.presentance.component.MoviePoster
import com.example.movieshow.ui.presentance.component.Shimmer
import com.example.movieshow.ui.presentance.component.simmer.CardShimmer
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.homeScreen.stateEvent.HomeEvent
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.homeScreen.stateEvent.HomeNetworkState
import com.example.movieshow.ui.presentance.navGraph.screenRoot.BottomRoute
import com.example.movieshow.ui.presentance.navGraph.screenRoot.ScreenRoute
import com.example.movieshow.ui.presentance.utils.Utils
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeNetworkState: HomeNetworkState,
    event: (HomeEvent) -> Unit
) {

    val context = LocalContext.current
    BackHandler(
        enabled = true
    ) {
        (context as Activity).finish()
    }


    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState {
        homeNetworkState.movieAutoSwipe.size
    }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        while (true) {
            delay(4000)
            coroutineScope.launch {
                if (pagerState.canScrollForward) {
                    pagerState.scrollToPage(pagerState.currentPage + 1)
                } else {
                    pagerState.animateScrollToPage(0)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .verticalScroll(scrollState)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            //firstSection
            SwipeItemSection(pagerState, homeNetworkState,navHostController)

            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )

            //secondSection

            SetItems(
                category = Utils.nowPlaying,
                homeNetworkState, navHostController)

            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )

            //thirdSection
            SetItems(
                category = Utils.topRated,
                homeNetworkState, navHostController)

            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )

            //forthSection
            SetItems(
                category = Utils.trending,
                homeNetworkState, navHostController)


            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )
            //popular
            SetItems(
                category = Utils.popular,
                homeNetworkState, navHostController)


            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )
            //upComing
            SetItems(
                category = Utils.upComing,
                homeNetworkState, navHostController)
        }
    }
}


@Composable
private fun SetItems(
    category : String,
    homeNetworkState: HomeNetworkState,
    navHostController: NavHostController
) {
    val title = when(category){
        Utils.nowPlaying -> stringResource(id = R.string.NowPlaying)
        Utils.trending -> stringResource(id = R.string.Trending)
        Utils.popular -> stringResource(id = R.string.Popular)
        Utils.topRated -> stringResource(id = R.string.TopRated)
       else-> stringResource(id = R.string.UpComing)
    }

    val mediaType = when(category){
        Utils.nowPlaying -> homeNetworkState.nowPlaying.take(10)
        Utils.trending ->  homeNetworkState.allTrending.take(10)
        Utils.popular ->  homeNetworkState.popularList.take(10)
        Utils.topRated -> homeNetworkState.topRatedList.take(10)
        else->  homeNetworkState.upComingList.take(10)
    }


    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "See all",
            style = MaterialTheme.typography.labelLarge,
            color =  MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            modifier = Modifier
                .clickable {
                    navHostController.navigate("${ScreenRoute.CommonScreen.route}/$title")
                }
        )

    }
    Spacer(
        modifier = Modifier
            .height(8.dp)
    )
    if (homeNetworkState.allTrending.isEmpty()) {
        LazyRow(
            state = rememberLazyListState(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            items(5) {
                Shimmer()
            }
        }

    } else {
        LazyRow(
            state = rememberLazyListState(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            items(mediaType,key = {it.id}) {movie->
                MoviePosterCard(movie = movie, onClick = {
                    navHostController.navigate("${BottomRoute.DetailsScreen.route}/${movie.id}")

                })
            }
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun SwipeItemSection(
    pagerState: PagerState,
    homeNetworkState: HomeNetworkState,
    navHostController: NavHostController
) {
    if (homeNetworkState.isLoading) {
        CardShimmer()
    } else {
        HorizontalPager(state = pagerState) {
            MovieCard(
                movie = homeNetworkState.nowPlaying[it],
                navHostController=navHostController
            )
        }
    }
    Spacer(
        modifier = Modifier
            .height(8.dp)
    )
    HorizontalPagerIndicator(
        pagerState = pagerState,
        pageCount = pagerState.pageCount,
        activeColor = MaterialTheme.colorScheme.secondary
    )
}


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun MovieCard(
    movie: Movie,
    navHostController: NavHostController
) {
    Box(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            onClick = {
                navHostController.navigate("${BottomRoute.DetailsScreen.route}/${movie.id}")
                Log.d("myTag","cardClick :${movie.id}")
            },
            elevation = 2.dp,
            shape = RoundedCornerShape(9.dp)
        ) {
            CustomImage(
                url = "${Utils.IMAGE_BASE_URL_original}${movie.backdrop_path}",
                modifier = Modifier
                    .fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 400f,
                            endY = 1000f,
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomStart
            ) {
                movie.title?.let { title ->
                    Text(
                        text = title,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .padding(
                                start = 6.dp,
                                top = 2.dp,
                                bottom = 2.dp,
                                end = 4.dp
                            )
                            .basicMarquee()
                    )
                }
            }

        }
    }

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoviePosterCard(
    movie: Movie,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .height(200.dp)
            .width(130.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            onClick = { onClick() },
            modifier = Modifier
                .fillMaxSize(),
            elevation = 2.dp,
            shape = RoundedCornerShape(18.dp),
            backgroundColor = MaterialTheme.colorScheme.surface
        ) {
            MoviePoster(
                url = "${Utils.IMAGE_BASE_URL_original}${movie.poster_path}",
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 400f,
                            endY = 1000f,
                        )
                    )
            )


        }

    }

}


