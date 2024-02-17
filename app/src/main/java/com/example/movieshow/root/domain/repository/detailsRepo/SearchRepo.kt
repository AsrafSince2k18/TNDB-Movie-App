package com.example.movieshow.root.domain.repository.detailsRepo

import com.example.movieshow.root.data.movieDetails.remote.respond.search.SearchData
import com.example.movieshow.root.response.Response
import kotlinx.coroutines.flow.Flow

interface SearchRepo {

    suspend fun searchData(
        query : String,
        page : Int
    ):Flow<Response<List<SearchData>>>

}