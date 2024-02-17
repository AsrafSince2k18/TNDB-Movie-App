package com.example.movieshow.ui.presentance.homeScreen.screenHandle.extraDetails.stateEvent

import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailMovie.similar.Similar
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailMovie.castCrew.MovieCast
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailMovie.details.MovieDetails
import com.example.movieshow.root.domain.model.Video

data class DetailsState(

    val isLoading : Boolean = false,

    val isError : String?=null,

    val currentId : Int?=null,

    var movieDetails : MovieDetails?=null,

    val videoList : List<Video> = emptyList(),

    val videoData : Video?=null,

    var videoClick : Boolean =false,

    val castAndCrew: MovieCast?=null,

    val similar: Similar?=null,

    )
