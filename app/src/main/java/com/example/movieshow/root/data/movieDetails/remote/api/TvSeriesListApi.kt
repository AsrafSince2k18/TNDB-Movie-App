package com.example.movieshow.root.data.movieDetails.remote.api

import com.example.movieshow.root.data.movieDetails.remote.respond.tvSeries.TvSeriesList
import com.example.movieshow.ui.presentance.utils.Utils.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvSeriesListApi {

    //https://api.themoviedb.org/3/tv/airing_today?language=en-US&page=1

    //top_rated

    @GET("tv/{category}")
    suspend fun topRated(
        @Path("category") category : String,
        @Query("language")language : String ="en-US",
        @Query("page") page : Int,
        @Query("api_key") apiKey : String = API_KEY
    ) : TvSeriesList


    @GET("tv/{category}")
    suspend fun airingToday(
        @Path("category") category : String,
        @Query("language")language : String ="en-US",
        @Query("page") page : Int,
        @Query("api_key") apiKey : String = API_KEY
    ) : TvSeriesList

    //on_the_air
    @GET("tv/{category}")
    suspend fun onTheAir(
        @Path("category") category : String,
        @Query("language")language : String ="en-US",
        @Query("page") page : Int,
        @Query("api_key") apiKey : String = API_KEY
    ) : TvSeriesList

    //popular
    @GET("tv/{category}")
    suspend fun popular(
        @Path("category") category : String,
        @Query("language")language : String ="en-US",
        @Query("page") page : Int,
        @Query("api_key") apiKey : String = API_KEY
    ) : TvSeriesList
}