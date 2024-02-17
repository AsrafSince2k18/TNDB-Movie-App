package com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.homeScreen.stateEvent

sealed class HomeEvent {

    data class OnQueryChange(val query : String) : HomeEvent()

    object PullToRefresh : HomeEvent()
    object ActiveSearchBar : HomeEvent()

    data class Paging(val category : String) : HomeEvent()

}