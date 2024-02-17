package com.example.movieshow.root.domain.repository.detailsRepo

import com.example.movieshow.root.domain.model.Tv
import com.example.movieshow.root.response.Response
import kotlinx.coroutines.flow.Flow

interface TvRepo {

    suspend fun topRated(
        category: String,
        page: Int
    ): Flow<Response<List<Tv>>>


    suspend fun airingToday(
        category: String,
        page: Int
    ): Flow<Response<List<Tv>>>


    suspend fun onTheAir(
        category: String,
        page: Int
    ): Flow<Response<List<Tv>>>


    suspend fun popular(
        category: String,
        page: Int
    ): Flow<Response<List<Tv>>>
}