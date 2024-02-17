package com.example.movieshow.ui.presentance.onBoard.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.movie.ui.presentance.onBoard.viewModel.SaveViewModel
import com.example.movieshow.ui.presentance.component.CustomButton
import com.example.movieshow.ui.presentance.navGraph.screenRoot.ScreenRoute
import com.example.movieshow.ui.presentance.onBoard.onBoardSealed.OnBoardSealed
import com.google.accompanist.pager.HorizontalPagerIndicator

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardScreen(navHostController: NavHostController,
                  saveViewModel: SaveViewModel = hiltViewModel()) {

    val pagerState = rememberPagerState {
        3
    }

    val item = listOf(
        OnBoardSealed.FirstScreen,
        OnBoardSealed.SecondScreen,
        OnBoardSealed.ThirdScreen,
    )

    Box(modifier = Modifier
        .fillMaxSize()) {
        TextButton(
            onClick = {
                      navHostController.navigate(ScreenRoute.OnMainScreen.route)
                        navHostController.popBackStack()
                saveViewModel.saveOnBoardScreen()
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
        ) {
            Text(text = "Skip",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp)
        }
        Column(
            modifier = Modifier
                .padding(18.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HorizontalPager(state = pagerState) {
                LottieSetUp(onBoardSealed = item[it])
            }
            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )
            HorizontalPagerIndicator(
                pagerState = pagerState,
                pageCount = pagerState.pageCount,
                indicatorShape = RectangleShape,
                activeColor = MaterialTheme.colorScheme.secondary

            )

            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )

            AnimationButton(pagerState = pagerState) {
                navHostController.navigate(ScreenRoute.OnMainScreen.route)
                navHostController.popBackStack()
                saveViewModel.saveOnBoardScreen()
            }
        }
    }
}


@Composable
fun LottieSetUp(onBoardSealed: OnBoardSealed) {

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(onBoardSealed.image))
    val progress by animateLottieCompositionAsState(composition = composition)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LottieAnimation(
            composition =composition,
            progress = { progress },
            modifier = Modifier
                .width(250.dp)
                .height(300.dp)
        )
        Text(text = onBoardSealed.title,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center)

        Spacer(modifier = Modifier
            .height(8.dp))

        Text(text = onBoardSealed.description,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center)

    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimationButton(pagerState: PagerState,
                onClick : () -> Unit){

    Row {
        AnimatedVisibility(visible = pagerState.currentPage ==2) {
            CustomButton(text = "Start", onClick = {
                onClick()
            },modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp))
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun On() {
    val navHostController = rememberNavController()
    OnBoardScreen( navHostController)
}