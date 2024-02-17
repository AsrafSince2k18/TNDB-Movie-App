package com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.tvScreen

import android.util.Log
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieshow.R
import com.example.movieshow.ui.presentance.component.HorizonatalGrid
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.tvScreen.stateEvent.TvEvent
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.tvScreen.stateEvent.TvState
import com.example.movieshow.ui.presentance.navGraph.screenRoot.BottomRoute
import com.example.movieshow.ui.presentance.utils.Utils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvCommonScreen(
    category : String?,
    tvState: TvState,
    tvEvent : (TvEvent) ->Unit,
    navHostController: NavHostController
) {

    val title = when(category){
        Utils.AiringToday -> stringResource(id = R.string.AiringToday)
        Utils.OnTheAir -> stringResource(id = R.string.OnTheAir)
        Utils.Popular -> stringResource(id = R.string.Popular)
        else -> stringResource(id = R.string.TopRated)
    }

    val tvCategory = when(category){
        Utils.AiringToday -> tvState.airingToday
        Utils.OnTheAir -> tvState.onTheAir
        Utils.Popular -> tvState.popular
        else ->tvState.tvTopRated
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

        if (tvCategory.isEmpty()) {
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
                items(tvCategory.size){index->

                    Log.d("myTag","index:= $index")
                    TvItem(tv = tvCategory[index],
                        onClick = {
                            navHostController.navigate("${BottomRoute.TvDetailsScreen.route}/${tvCategory[index].id}")
                        })

                    if(index>=tvCategory.size-1 && !tvState.isLoading){
                        if(category!=null){
                            tvEvent(TvEvent.PagingTv(title))
                        }
                    }
                }
            }
        }
    }

}