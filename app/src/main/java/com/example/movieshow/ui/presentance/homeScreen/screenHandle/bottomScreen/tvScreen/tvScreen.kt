package com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.tvScreen

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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieshow.R
import com.example.movieshow.root.domain.model.Tv
import com.example.movieshow.ui.presentance.component.CustomImage
import com.example.movieshow.ui.presentance.component.MoviePoster
import com.example.movieshow.ui.presentance.component.Shimmer
import com.example.movieshow.ui.presentance.component.simmer.CardShimmer
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.tvScreen.stateEvent.TvState
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.homeSetUp.BottomItems
import com.example.movieshow.ui.presentance.navGraph.screenRoot.BottomRoute
import com.example.movieshow.ui.presentance.navGraph.screenRoot.ScreenRoute
import com.example.movieshow.ui.presentance.utils.Utils
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TvScreen(
    navHostController: NavHostController,
    selectedItem: MutableState<Int>,
    tvState: TvState
) {
    BackHandler(enabled = true) {
        selectedItem.value = 0
        navHostController.navigate(BottomItems.HomeScreen.route)
    }

    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState {
        tvState.airingSlide.size
    }
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
            TvScreenDetails(
                category = Utils.AiringToday,
                tvState = tvState,
                navHostController = navHostController
            )
            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )

            TvSlide(
                tvState = tvState,
                pagerState = pagerState,
                navHostController=navHostController
            )

            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )

            TvScreenDetails(
                category = Utils.Popular,
                tvState = tvState,
                navHostController = navHostController
            )

            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )

            TvScreenDetails(
                category = Utils.topRated,
                tvState = tvState,
                navHostController = navHostController
            )

            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )

            TvScreenDetails(
                category = Utils.OnTheAir,
                tvState = tvState,
                navHostController = navHostController
            )
        }
    }
}


@Composable
fun TvScreenDetails(
    category: String,
    tvState: TvState,
    navHostController: NavHostController
) {

    val title = when (category) {
        Utils.AiringToday -> stringResource(id = R.string.AiringToday)
        Utils.OnTheAir -> stringResource(id = R.string.OnTheAir)
        Utils.Popular -> stringResource(id = R.string.Popular)
        else -> stringResource(id = R.string.TopRated)
    }

    val tvCategory = when (category) {
        Utils.AiringToday -> tvState.airingToday.take(10)
        Utils.OnTheAir -> tvState.onTheAir.take(10)
        Utils.Popular -> tvState.popular.take(10)
        else -> tvState.tvTopRated.take(10)
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
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            modifier = Modifier
                .clickable {
                    navHostController.navigate("${ScreenRoute.TvCommonScreen.route}/$title")
                }
        )

    }
    Spacer(
        modifier = Modifier
            .height(8.dp)
    )
    if (tvCategory.isEmpty()) {
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
            items(tvCategory, key = { it.id }) { tvItem ->
                TvPosterCard(tv = tvItem, onClick = {
                    navHostController.navigate("${BottomRoute.TvDetailsScreen.route}/${tvItem.id}")

                })
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TvPosterCard(
    tv: Tv,
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
                url = "${Utils.IMAGE_BASE_URL_original}${tv.poster_path}",
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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TvSlide(
    tvState: TvState,
    pagerState: PagerState,
    navHostController: NavHostController
) {


    if (tvState.isLoading) {
        CardShimmer()
    } else {
        HorizontalPager(state = pagerState) {
            TvSlideCard(
                tv = tvState.airingSlide[it],
               navHostController=navHostController
            )
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

}


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun TvSlideCard(
    tv: Tv,
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
                navHostController.navigate(
                    "${BottomRoute.TvDetailsScreen.route}/${tv.id}")
            },
            elevation = 2.dp,
            shape = RoundedCornerShape(9.dp)
        ) {
            CustomImage(
                url = "${Utils.IMAGE_BASE_URL_original}${tv.backdrop_path}",
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
                tv.original_name?.let { title ->
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



