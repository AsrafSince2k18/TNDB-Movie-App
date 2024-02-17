package com.example.movieshow.ui.presentance.navGraph.graph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.homeScreen.HomeScreen
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.homeScreen.viewModel.HomeViewModel
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.homeSetUp.MediaMainScreen
import com.example.movieshow.ui.presentance.navGraph.screenRoot.ScreenRoute
import com.example.movieshow.ui.presentance.onBoard.screen.OnBoardScreen

@Composable
fun NavGraph(navHostController: NavHostController,
             startDestination: String) {

    NavHost(
        navController =navHostController,
        startDestination = startDestination,
    ){
        composable(route = ScreenRoute.OnBoardScreen.route){
            OnBoardScreen(navHostController=navHostController)
        }
        composable(route = ScreenRoute.OnMainScreen.route){
            MediaMainScreen()
        }
    }


}