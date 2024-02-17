package com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.searchScreen.stateEvent

sealed class SearchEvent {

    data class OnValueChange(val query: String) : SearchEvent()
    data class SearchActive(val active: Boolean) : SearchEvent()
    data class Paginate(val page: Int) : SearchEvent()

    object OnSearch : SearchEvent()
}