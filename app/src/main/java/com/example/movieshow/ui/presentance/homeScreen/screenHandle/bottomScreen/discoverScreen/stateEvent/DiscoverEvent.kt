package com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.discoverScreen.stateEvent

sealed class DiscoverEvent {


    data class DiscoverItem(val item : Int) : DiscoverEvent()

    data class DiscoverMovieItem(val category : String) : DiscoverEvent()


}