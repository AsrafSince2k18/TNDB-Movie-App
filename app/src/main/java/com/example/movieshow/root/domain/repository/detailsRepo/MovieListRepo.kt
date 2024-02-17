package com.example.movieshow.root.domain.repository.detailsRepo

import com.example.movieshow.root.domain.model.Movie
import com.example.movieshow.root.response.Response
import kotlinx.coroutines.flow.Flow

interface MovieListRepo {

    suspend fun getMovieList(
        forceFetchScreen : Boolean,
        category : String,
        page : Int
    ) : Flow<Response<List<Movie>>>

    suspend fun getAllTrending(
        category : String,
    ) : Flow<Response<List<Movie>>>

    suspend fun getPopular(
        forceFetchScreen : Boolean,
        category : String,
        page : Int
    ) : Flow<Response<List<Movie>>>

    suspend fun getTopRated(
        forceFetchScreen : Boolean,
        category : String,
        page : Int
    ) : Flow<Response<List<Movie>>>

    suspend fun getUpcoming(
        forceFetchScreen : Boolean,
        category : String,
        page : Int
    ) : Flow<Response<List<Movie>>>

}