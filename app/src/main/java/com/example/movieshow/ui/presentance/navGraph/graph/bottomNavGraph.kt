package com.example.movieshow.ui.presentance.navGraph.graph

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.discoverScreen.DiscoverScreen
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.discoverScreen.viewModel.DiscoverViewModel
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.homeScreen.CommonItemScreen
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.homeScreen.HomeScreen
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.homeScreen.viewModel.HomeViewModel
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.searchScreen.SearchScreen
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.searchScreen.viewModel.SearchViewModel
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.tvScreen.TvCommonScreen
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.tvScreen.TvScreen
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.tvScreen.viewModel.TvViewModel
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.extraDetails.ExtraDetails
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.extraDetails.viewModel.DetailsViewModel
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.extraDetailsTv.ExtraDetailsTv
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.extraDetailsTv.viewModel.DetailsTvViewModel
import com.example.movieshow.ui.presentance.navGraph.screenRoot.BottomRoute
import com.example.movieshow.ui.presentance.navGraph.screenRoot.ScreenRoute

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun BottomNavGraph(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    selectedItem: MutableState<Int>
) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val state by homeViewModel.homeState.collectAsState()

    val tvViewModel = hiltViewModel<TvViewModel>()
    val tvState by tvViewModel.tvState.collectAsState()

    NavHost(
        navController = navHostController,
        startDestination = BottomRoute.HomeScreen.route,
        modifier = modifier
    ) {

        composable(route = BottomRoute.HomeScreen.route) {

            HomeScreen(
                navHostController = navHostController,
                homeNetworkState = state,
                event = homeViewModel::onEvent
            )

        }

        composable(
            route = "${ScreenRoute.CommonScreen.route}/{title}",
            arguments = listOf(navArgument("title") {
                type = NavType.StringType
            })
        ) {navBackStackEntry->

            val getKey = navBackStackEntry.arguments?.getString("title")
            CommonItemScreen(
                navHostController = navHostController,
                getKey=getKey,
                homeNetworkState=state,
                event = homeViewModel::onEvent
            )
        }

        composable(route = BottomRoute.DiscoverScreen.route) {
            val viewModel = hiltViewModel<DiscoverViewModel>()
            val discoverState by viewModel.state.collectAsState()
            DiscoverScreen(
                navHostController = navHostController,
                selectedItem = selectedItem,
                discoverState = discoverState,
                event = viewModel::onEvent
            )
        }
        composable(route = BottomRoute.TvScreen.route) {

            TvScreen(
                navHostController = navHostController,
                selectedItem = selectedItem,
                tvState = tvState
            )
        }

        composable(route = "${ScreenRoute.TvCommonScreen.route}/{title}",
            arguments = listOf(navArgument(name="title"){
                type= NavType.StringType
            })
        ){navBackStackEntry->
            val getKey = navBackStackEntry.arguments?.getString("title")
            TvCommonScreen(
                category = getKey,
                tvState = tvState,
                navHostController = navHostController,
                tvEvent = tvViewModel::tvEvent
            )
        }

        composable(route = BottomRoute.SearchScreen.route) {

            val searchViewModel = hiltViewModel<SearchViewModel>()
            val searchState by searchViewModel.searchState.collectAsState()
            SearchScreen(
                navHostController = navHostController,
                selectedItem = selectedItem,
                searchState = searchState,
                searchEvent = searchViewModel::searchEvent
            )
        }


        composable(route = "${BottomRoute.DetailsScreen.route}/{id}",
            arguments = listOf(navArgument(name="id"){
                type= NavType.IntType
                defaultValue=-1
            })
        ){navBackStackEntry->
            val detailsViewModel = hiltViewModel<DetailsViewModel>()
            val detailState by detailsViewModel.state.collectAsStateWithLifecycle()
            ExtraDetails(
                navHostController=navHostController,
                navBackStackEntry=navBackStackEntry,
                detailsState =detailState,
                event = detailsViewModel::onEvent
            )
        }

        composable(route = "${BottomRoute.TvDetailsScreen.route}/{id}",
            arguments = listOf(navArgument(name="id"){
                type= NavType.IntType
                defaultValue=-1
            })
        ){navBackStackEntry->
            val detailsTvViewModel = hiltViewModel<DetailsTvViewModel>()
            val detailState by detailsTvViewModel.state.collectAsStateWithLifecycle()
            ExtraDetailsTv(
                navHostController=navHostController,
                navBackStackEntry=navBackStackEntry,
                detailsState =detailState,
                event = detailsTvViewModel::onEvent
            )
        }

    }

}