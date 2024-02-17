package com.example.movieshow.root.data.movieDetails.remote.api

import com.example.movieshow.root.data.movieDetails.remote.respond.search.SearchList
import com.example.movieshow.ui.presentance.utils.Utils.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {


    @GET("search/multi")
    suspend fun getSearch(
        @Query("query") query : String,
        @Query("language") language: String = "en-US",
        @Query("page") page : Int,
        @Query("api_key") apiKey : String =API_KEY
    ):SearchList

}