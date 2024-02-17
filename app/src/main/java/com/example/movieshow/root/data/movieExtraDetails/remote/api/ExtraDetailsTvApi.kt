package com.example.movieshow.root.data.movieExtraDetails.remote.api

import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailTv.castCrew.CastCrew
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailTv.details.TvDetails
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailTv.smilar.TvSimilar
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailTv.video.TvVideo
import com.example.movieshow.ui.presentance.utils.Utils
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ExtraDetailsTvApi {

    @GET("tv/{series_id}/videos")
    suspend fun getSeriesVideo(
        @Path("series_id") id : Int,
        @Query("language") language : String = "en-US",
        @Query("api_key") apiKey : String = Utils.API_KEY
    ):TvVideo

    @GET("tv/{series_id}")
    suspend fun getTvDetails(
        @Path("series_id") id : Int,
        @Query("language") language : String = "en-US",
        @Query("api_key") apiKey : String = Utils.API_KEY
    ):TvDetails


    @GET("tv/{series_id}/aggregate_credits")
    suspend fun getTvCastCrew(
        @Path("series_id") id : Int,
        @Query("language") language : String = "en-US",
        @Query("api_key") apiKey : String = Utils.API_KEY
    ):CastCrew


    @GET("tv/{series_id}/similar")
    suspend fun getTvSimilar(
        @Path("series_id") id : Int,
        @Query("language") language : String = "en-US",
        @Query("api_key") apiKey : String = Utils.API_KEY
    ):TvSimilar
}