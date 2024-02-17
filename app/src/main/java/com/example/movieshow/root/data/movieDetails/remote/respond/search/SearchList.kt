package com.example.movieshow.root.data.movieDetails.remote.respond.search

data class SearchList(
    val page: Int,
    val results: List<SearchData>,
    val total_pages: Int,
    val total_results: Int
)