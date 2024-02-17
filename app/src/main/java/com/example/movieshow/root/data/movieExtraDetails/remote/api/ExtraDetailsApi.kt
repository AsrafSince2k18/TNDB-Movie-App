package com.example.movieshow.root.data.movieExtraDetails.remote.api

import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailMovie.similar.Similar
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailMovie.castCrew.MovieCast
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailMovie.details.MovieDetails
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailMovie.video.VideoList
import com.example.movieshow.ui.presentance.utils.Utils.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ExtraDetailsApi {

    @GET("movie/{movie_id}/videos")
    suspend fun movieVideo(
        @Path("movie_id") movieId : Int,
        @Query("language") language : String ="en-US",
        @Query("api_key") apiKey : String = API_KEY
    ) : VideoList

    @GET("movie/{movie_id}")
    suspend fun movieDetails(
        @Path("movie_id") movieId : Int,
        @Query("language") language : String ="en-US",
        @Query("api_key") apiKey : String = API_KEY
    ) : MovieDetails

    //movie/{movie_id}/credits
    @GET("movie/{movie_id}/credits")
    suspend fun movieCastAndCrew(
        @Path("movie_id") movieId : Int,
        @Query("language") language : String ="en-US",
        @Query("api_key") apiKey : String = API_KEY
    ) : MovieCast

//

    @GET("movie/{movie_id}/similar")
    suspend fun movieSimilar(
        @Path("movie_id") movieId : Int,
        @Query("language") language : String ="en-US",
        @Query("api_key") apiKey : String = API_KEY
    ) : Similar

    //movie/{movie_id}/videos
    //video
    // movie/848326/videos?language=en-US&api_key=3d0ca977a6482410c490e3519e1323fa
    //https://api.themoviedb.org/3/movie//videos?language=en-US&api_key=3



}