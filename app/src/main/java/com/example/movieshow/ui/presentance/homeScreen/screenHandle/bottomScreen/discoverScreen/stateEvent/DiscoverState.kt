package com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.discoverScreen.stateEvent

import androidx.compose.runtime.mutableIntStateOf
import com.example.movieshow.root.domain.model.Movie
import com.example.movieshow.root.domain.model.Tv

data class DiscoverState(

    val isLoading: Boolean = false,

    val isError: String?=null,

    var itemSelected: Int = mutableIntStateOf(0).value,

    val items: List<String> = listOf("Movie","Tv"),

    var discoverCategory :String= "movie",

    val movieDiscover : Movie?=null,

    val discoverMovieList: List<Movie> = emptyList(),

    val discoverTvList: List<Tv> = emptyList(),

    val discoverMoviePage : Int = 1,

    val discoverTvPage : Int = 1,

    )
