package com.example.movieshow.root.data.movieDetails.remote.api

import com.example.movieshow.root.data.movieDetails.remote.respond.MovieLists
import com.example.movieshow.root.data.movieDetails.remote.respond.tvSeries.TvSeriesList
import com.example.movieshow.ui.presentance.utils.Utils.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDiscoverTvApi {

    //https://api.themoviedb.org/3/discover/movie?include_adult=false&
    // include_video=false&language=en-US&page=1&sort_by=popularity.desc

    @GET("discover/{category}")
    suspend fun getDiscoverMovie(
        @Path("category") category:  String,
        @Query("include_adult") include_adult : Boolean =false,
        @Query("include_video")include_video:Boolean=false,
        @Query("language") language: String = "en-US",
        @Query("page")page : Int,
        @Query("sort_by")sort_by:String="popularity.desc",
        @Query("api_key")apiKey : String =API_KEY
    ):MovieLists

    @GET("discover/{category}")
    suspend fun getDiscoverTv(
        @Path("category") category:  String,
        @Query("include_adult") include_adult : Boolean =false,
        @Query("include_video")include_video:Boolean=false,
        @Query("language") language: String = "en-US",
        @Query("page")page : Int,
        @Query("sort_by")sort_by:String="popularity.desc",
        @Query("api_key")apiKey : String =API_KEY
    ):TvSeriesList

}