package com.example.movieshow.ui.presentance.homeScreen.screenHandle.extraDetails.stateEvent

sealed class DetailsEvent {


    data class GetDetails(val id:Int) : DetailsEvent()

    data class CurrentId(val currentId : Int) : DetailsEvent()

    data class VideoClick(val click : Boolean) : DetailsEvent()
}