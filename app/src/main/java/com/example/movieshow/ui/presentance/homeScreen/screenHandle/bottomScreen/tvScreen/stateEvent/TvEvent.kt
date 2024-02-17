package com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.tvScreen.stateEvent

sealed class TvEvent {

    data class PagingTv(val page: String) : TvEvent()

}