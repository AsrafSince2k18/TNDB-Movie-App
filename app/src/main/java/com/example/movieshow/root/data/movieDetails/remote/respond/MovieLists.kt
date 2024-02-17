package com.example.movieshow.root.data.movieDetails.remote.respond

data class MovieLists(
    val dates: Dates,
    val page: Int,
    val results: List<MovieListData>,
    val total_pages: Int,
    val total_results: Int
)