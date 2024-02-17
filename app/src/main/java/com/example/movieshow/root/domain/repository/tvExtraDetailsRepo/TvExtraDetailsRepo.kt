package com.example.movieshow.root.domain.repository.tvExtraDetailsRepo

import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailTv.castCrew.CastCrew
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailTv.details.TvDetails
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailTv.smilar.TvSimilar
import com.example.movieshow.root.domain.model.TvVideos
import com.example.movieshow.root.response.Response
import kotlinx.coroutines.flow.Flow

interface TvExtraDetailsRepo  {

    suspend fun getTvDetails(id:Int): Flow<Response<TvDetails>>

    suspend fun getTvVideo(id:Int): Flow<Response<List<TvVideos>>>

    suspend fun getTvCastAndCrew(id:Int): Flow<Response<CastCrew>>

    suspend fun getTvSimilar(id:Int): Flow<Response<TvSimilar>>

}