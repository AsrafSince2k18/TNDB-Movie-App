package com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailMovie.similar

data class Similar(
    val page: Int,
    val results: List<SimilarList>,
    val total_pages: Int,
    val total_results: Int
)