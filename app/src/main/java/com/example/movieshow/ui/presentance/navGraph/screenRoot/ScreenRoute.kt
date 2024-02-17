package com.example.movieshow.ui.presentance.navGraph.screenRoot

sealed class ScreenRoute(val route : String) {


    object OnBoardScreen : ScreenRoute(route = "on_board")

    object OnMainScreen : ScreenRoute(route = "main_screen")

    object CommonScreen : ScreenRoute(route = "common_screen")

    object TvCommonScreen : ScreenRoute(route = "tv_common_screen")



}