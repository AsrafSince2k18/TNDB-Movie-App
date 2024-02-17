package com.example.movieshow.ui.presentance.homeScreen.screenHandle.extraDetailsTv.stateEvent

sealed class DetailsTvEvent {

    data class CurrentId(val currentId : Int) : DetailsTvEvent()

    data class VideoClick(val click : Boolean) : DetailsTvEvent()
}