package com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.homeScreen.stateEvent

sealed class HomeEvent {

    data class Paging(val category : String) : HomeEvent()

}