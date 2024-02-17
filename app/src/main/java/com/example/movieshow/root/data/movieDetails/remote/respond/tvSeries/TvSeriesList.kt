package com.example.movieshow.root.data.movieDetails.remote.respond.tvSeries

data class TvSeriesList(
    val page: Int,
    val results: List<TvSeriesData>,
    val total_pages: Int,
    val total_results: Int
)