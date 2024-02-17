package com.example.movieshow.root.domain.repository.extraDetailsRepo

import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailMovie.similar.Similar
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailMovie.castCrew.MovieCast
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailMovie.details.MovieDetails
import com.example.movieshow.root.domain.model.Video
import com.example.movieshow.root.response.Response
import kotlinx.coroutines.flow.Flow

interface MovieExtraDetailsRepo {

    suspend fun getDetails(id:Int):Flow<Response<MovieDetails>>

    suspend fun getVideo(id:Int):Flow<Response<List<Video>>>

    suspend fun getCastAndCrew(id:Int): Flow<Response<MovieCast>>

    suspend fun getSimilar(id:Int): Flow<Response<Similar>>

}