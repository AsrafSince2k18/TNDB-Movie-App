package com.example.movieshow.ui.presentance.homeScreen.screenHandle.extraDetailsTv.stateEvent

import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailTv.castCrew.CastCrew
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailTv.details.TvDetails
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailTv.smilar.TvSimilar
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailTv.video.TvVideoResult
import com.example.movieshow.root.domain.model.TvVideos

data class DetailsTvState(

    val isLoading : Boolean = false,

    val isError : String?=null,

    val currentId : Int?=null,

    var tvDetails : TvDetails?=null,

    val tvVideoList : List<TvVideos> = emptyList(),

    val tvVideoData : TvVideos?=null,

    var tvVideoClick : Boolean =false,

    val castAndCrew: CastCrew?=null,

    val similar: TvSimilar?=null,

    )
