package com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.homeScreen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.movieshow.R
import com.example.movieshow.ui.presentance.component.HorizonatalGrid
import com.example.movieshow.ui.presentance.component.MovieItem
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.homeScreen.stateEvent.HomeEvent
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.homeScreen.stateEvent.HomeNetworkState
import com.example.movieshow.ui.presentance.navGraph.screenRoot.BottomRoute
import com.example.movieshow.ui.presentance.utils.Utils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonItemScreen(
    navHostController: NavHostController,
    getKey: String?,
    homeNetworkState: HomeNetworkState,
    event: (HomeEvent) -> Unit
) {



    val context=LocalContext.current
    Log.d("myTags", "starting " + getKey.toString())

    val title = when (getKey) {
        Utils.nowPlaying -> stringResource(id = R.string.NowPlaying)
        Utils.trending -> stringResource(id = R.string.Trending)
        Utils.popular -> stringResource(id = R.string.Popular)
        Utils.topRated -> stringResource(id = R.string.TopRated)
        else -> stringResource(id = R.string.UpComing)
    }
    Log.d("myTag", "middle " + title)

    val mediaCategory = when (getKey) {
        Utils.nowPlaying -> homeNetworkState.nowPlaying
        Utils.trending -> homeNetworkState.allTrending
        Utils.popular -> homeNetworkState.popularList
        Utils.topRated -> homeNetworkState.topRatedList
        else -> {
            homeNetworkState.upComingList
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    navHostController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = "back"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            )
        )

        if (mediaCategory.isEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                state = rememberLazyGridState(),
                modifier = Modifier
            ) {
                items(6) {
                    HorizonatalGrid()
                }
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                state = rememberLazyGridState()

            ) {
                items(mediaCategory.size){index->
                    //Log.d("myTag","index:= $index")
                    MovieItem(movie = mediaCategory[index],
                        onClick = {
                            Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
                            navHostController.navigate("${BottomRoute.DetailsScreen.route}/${mediaCategory[index].id}")
                        })

                    //Log.d("myTag","indexSize:= $getKey")



                    if(index>=mediaCategory.size-1 && !homeNetworkState.isLoading){
                        if(getKey!=null){
                            event(HomeEvent.Paging(getKey))
                        }
                    }
                    //Log.d("myTag","indexSize:= $getKey")
                }
            }
        }
    }

}