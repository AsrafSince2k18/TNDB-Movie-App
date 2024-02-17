package com.example.movieshow.root.data.movieDetails.remote.api

import com.example.movieshow.root.data.movieDetails.remote.respond.MovieLists
import com.example.movieshow.ui.presentance.utils.Utils.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieListApi {

    @GET("movie/{category}")
    suspend fun getMovieList(
        @Path("category") category : String,
        @Query("language") language : String ="en-US",
        @Query("page") page : Int,
        @Query("api_key") apiKey : String = API_KEY
    ) : MovieLists

    //https://api.themoviedb.org/3/trending/all/day?language=en-US
    @GET("{category}/all/day")
    suspend fun getAllTrending(
        @Path("category") category : String,
        @Query("language") language : String ="en-US",
        @Query("api_key") apiKey : String = API_KEY
    ) : MovieLists

    @GET("movie/{category}")
    suspend fun getPopular(
        @Path("category") category : String,
        @Query("language") language : String ="en-US",
        @Query("page") page : Int,
        @Query("api_key") apiKey : String = API_KEY
    ) : MovieLists

    @GET("movie/{category}")
    suspend fun getTopRated(
        @Path("category") category : String,
        @Query("language") language : String ="en-US",
        @Query("page") page : Int,
        @Query("api_key") apiKey : String = API_KEY
    ) : MovieLists

    @GET("movie/{category}")
    suspend fun getUpComing(
        @Path("category") category : String,
        @Query("language") language : String ="en-US",
        @Query("page") page : Int,
        @Query("api_key") apiKey : String = API_KEY
    ) : MovieLists
}