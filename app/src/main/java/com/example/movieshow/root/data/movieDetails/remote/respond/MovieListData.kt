package com.example.movieshow.root.data.movieDetails.remote.respond

data class MovieListData(
    val adult: Boolean?,
    val backdrop_path: String?,
    val genre_ids: List<Int>?,
    val id: Int?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: String?,
    val vote_average: Double?,
    val vote_count: Int?
)