package com.example.movieshow.root.data.maper

import com.example.movieshow.root.data.movieDetails.remote.respond.tvSeries.TvSeriesData
import com.example.movieshow.root.domain.model.Tv

fun TvSeriesData.toTvMapper(): Tv {
    return Tv(
        adult =adult,
        backdrop_path =backdrop_path,
        first_air_date =first_air_date,
        genre_ids = try {
            genre_ids.joinToString(",")
        } catch (e: Exception) {
            "-1,-2"
        },
        id =id,
        name =name,
        origin_country =origin_country,
        original_language =original_language,
        original_name =original_name,
        overview =overview,
        popularity =popularity,
        poster_path =poster_path,
        vote_average =vote_average,
        vote_count =vote_count
    )

}