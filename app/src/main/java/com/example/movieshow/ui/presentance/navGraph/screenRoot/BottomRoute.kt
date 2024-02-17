package com.example.movieshow.ui.presentance.navGraph.screenRoot

sealed class BottomRoute (val route : String){

    object HomeScreen : BottomRoute(route = "home_screen")
    object DiscoverScreen : BottomRoute(route = "discover_screen")
    object TvScreen : BottomRoute(route = "tv_route")
    object SearchScreen : BottomRoute(route = "favorite_screen")

    object DetailsScreen : BottomRoute(route = "details_screen")

    object TvDetailsScreen : BottomRoute(route = "tv_details_screen")

}