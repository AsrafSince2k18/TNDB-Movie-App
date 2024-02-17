package com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailTv.smilar

data class TvSimilar(
    val page: Int,
    val results: List<TvSimilarData>,
    val total_pages: Int,
    val total_results: Int
)