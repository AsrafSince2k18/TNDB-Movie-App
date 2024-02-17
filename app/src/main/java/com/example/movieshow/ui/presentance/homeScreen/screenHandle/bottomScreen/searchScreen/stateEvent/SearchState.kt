package com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.searchScreen.stateEvent

import com.example.movieshow.root.data.movieDetails.remote.respond.search.SearchData

data class SearchState(

    val isLoading : Boolean= false,

    val isError : String?=null,

    var query : String="",

    val searchBarActive : Boolean =false,

    val searchListData : List<SearchData> = emptyList(),

    val searchPage : Int = 1

)
