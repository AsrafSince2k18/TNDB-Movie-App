package com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.tvScreen.stateEvent

import androidx.compose.runtime.mutableIntStateOf
import com.example.movieshow.root.domain.model.Tv

data class TvState(

    val isLoading : Boolean = false,

    val isError : String ?=null,

    val tvTopRated : List<Tv> = emptyList(),

    val airingToday : List<Tv> = emptyList(),

    val airingSlide : List<Tv> = emptyList(),

    val onTheAir : List<Tv> = emptyList(),

    val popular : List<Tv> = emptyList(),


    val tvTopRatedPage : Int =1,

    val airingTodayPage : Int =1,

    val onTheAirPage : Int=1,

    val popularPage : Int=1,

    val itemSelected : Int = mutableIntStateOf(0).value
)
