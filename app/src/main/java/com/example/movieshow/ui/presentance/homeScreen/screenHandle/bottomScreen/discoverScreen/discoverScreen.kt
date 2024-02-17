package com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.discoverScreen

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Movie
import androidx.compose.material.icons.sharp.Tv
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieshow.ui.presentance.component.HorizonatalGrid
import com.example.movieshow.ui.presentance.component.MovieItem
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.discoverScreen.stateEvent.DiscoverEvent
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.discoverScreen.stateEvent.DiscoverState
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.homeSetUp.BottomItems
import com.example.movieshow.ui.presentance.navGraph.screenRoot.BottomRoute


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverScreen(
    navHostController: NavHostController,
    selectedItem: MutableState<Int>,
    discoverState: DiscoverState,
    event: (DiscoverEvent) -> Unit
) {

    BackHandler(enabled = true) {
        selectedItem.value = 0
        navHostController.navigate(BottomItems.HomeScreen.route)
    }

    LaunchedEffect(key1 = Unit) {
        event(DiscoverEvent.DiscoverItem(discoverState.itemSelected))
    }


    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SingleChoiceSegmentedButtonRow(
                    space = 12.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    discoverState.items.forEachIndexed { index, s ->
                        SegmentedButton(
                            selected = discoverState.itemSelected == index,
                            onClick = {
                                event(DiscoverEvent.DiscoverItem(index))
                                when (index) {
                                    0 -> {
                                        Log.d("myTag", "0:= ${discoverState.discoverCategory}")
                                        event(DiscoverEvent.DiscoverItem(index))
                                        
                                    }
                                    1 -> {
                                        Log.d("myTag", "1:= ${discoverState.discoverCategory}")
                                        event(DiscoverEvent.DiscoverItem(index))


                                    }
                                }

                            },
                            shape = SegmentedButtonDefaults.baseShape.copy(
                                CornerSize(9.dp)
                            ),
                            colors = SegmentedButtonDefaults.colors(
                                activeContainerColor = MaterialTheme.colorScheme.surface,
                                inactiveContainerColor = MaterialTheme.colorScheme.surface
                                    .copy(alpha = 0.5f),
                            ),
                            icon = {
                                Icon(
                                    imageVector = if (index == 0) Icons.Sharp.Movie
                                    else Icons.Sharp.Tv,
                                    contentDescription = s
                                )
                            }
                        ) {
                            Text(
                                text = s,
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }

                }

            }


            if(discoverState.itemSelected==0){
                movie(discoverState, navHostController, event)

            }else{
                tv(discoverState, navHostController, event)
            }


        }


    }

}

@Composable
private fun movie(
    discoverState: DiscoverState,
    navHostController: NavHostController,
    event: (DiscoverEvent) -> Unit
) {
    val discoverCategory = discoverState.discoverMovieList
    if (discoverCategory.isEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = rememberLazyGridState(),
            modifier = Modifier
        ) {
            items(6) {
                HorizonatalGrid()
            }
        }
    } else if (discoverState.isError != null) {
        Text(text = discoverState.isError)
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = rememberLazyGridState()
        ) {
            items(discoverCategory.size) { index ->
                MovieItem(discoverCategory[index], onClick = {
                    navHostController.navigate("${BottomRoute.DetailsScreen.route}/${discoverCategory[index].id}")
                })
                if (index >= discoverCategory.size - 1 && !discoverState.isLoading) {
                    event(DiscoverEvent.DiscoverItem(discoverState.itemSelected))
                }
            }
        }
    }
}


@Composable
private fun tv(
    discoverState: DiscoverState,
    navHostController: NavHostController,
    event: (DiscoverEvent) -> Unit
) {
    val discoverCategory = discoverState.discoverTvList
    if (discoverCategory.isEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = rememberLazyGridState(),
            modifier = Modifier
        ) {
            items(6) {
                HorizonatalGrid()
            }
        }
    } else if (discoverState.isError != null) {
        Text(text = discoverState.isError)
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = rememberLazyGridState()
        ) {
            items(discoverCategory.size) { index ->
                DiscoverItemTv(discoverCategory[index], onClick = {
                    navHostController.navigate("${BottomRoute.TvDetailsScreen.route}/${discoverCategory[index].id}")
                })
                if (index >= discoverCategory.size - 1 && !discoverState.isLoading) {
                    event(DiscoverEvent.DiscoverItem(discoverState.itemSelected))
                }
            }
        }
    }
}




