package com.example.movieshow.root.domain.repository.detailsRepo

import com.example.movieshow.root.domain.model.Movie
import com.example.movieshow.root.domain.model.Tv
import com.example.movieshow.root.response.Response
import kotlinx.coroutines.flow.Flow

interface DiscoverRepo {

    suspend fun discoverMovie(
        category : String,
        page : Int
    ):Flow<Response<List<Movie>>>


    suspend fun discoverTv(
        category : String,
        page : Int
    ):Flow<Response<List<Tv>>>

}