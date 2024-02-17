package com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailTv.details

data class TvDetails(
    val adult: Boolean?,
    val backdrop_path: Any?,
    val episode_run_time: List<Any>,
    val first_air_date: String?,
    val homepage: String?,
    val id: Int,
    val in_production: Boolean?,
    val languages: List<String>?,
    val last_air_date: Any?,
    val name: String?,
    val next_episode_to_air: Any?,
    val number_of_episodes: Int?,
    val number_of_seasons: Int?,
    val original_language: String?,
    val original_name: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val status: String?,
    val tagline: String?,
    val type: String?,
    val vote_average: Double?,
    val vote_count: Int?
)