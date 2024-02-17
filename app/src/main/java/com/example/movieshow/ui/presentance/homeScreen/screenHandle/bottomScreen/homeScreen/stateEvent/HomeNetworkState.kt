package com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.homeScreen.stateEvent

import com.example.movieshow.root.domain.model.Movie

data class HomeNetworkState(

    val query: String = "",

    val isLoading: Boolean = true,

    val isError: String? = null,

    val nowPlaying: List<Movie> = emptyList(),

    val movieAutoSwipe: List<Movie> = emptyList(),

    val allTrending: List<Movie> = emptyList(),

    val popularList: List<Movie> = emptyList(),

    val topRatedList: List<Movie> = emptyList(),

    val upComingList: List<Movie> = emptyList(),

    var popularUpComingPage : Int = 1,
    var topRatedUpComingPage : Int = 1,
    var trendingUpComingPage : Int = 1,
    var upComingMovieUpComingPage : Int = 1,
    var nowPlayingUpComingPage : Int = 1,


)

