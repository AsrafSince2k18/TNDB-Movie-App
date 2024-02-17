package com.example.movieshow.root.data.repository.tvExtraDetailsRepoImpl

import com.example.movieshow.root.data.maper.toTvVideo
import com.example.movieshow.root.data.movieExtraDetails.remote.api.ExtraDetailsTvApi
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailTv.castCrew.CastCrew
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailTv.details.TvDetails
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailTv.smilar.TvSimilar
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailTv.video.TvVideoResult
import com.example.movieshow.root.domain.model.TvVideos
import com.example.movieshow.root.domain.repository.tvExtraDetailsRepo.TvExtraDetailsRepo
import com.example.movieshow.root.response.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class TvExtraDetailsRepoImpl (
    private val tvExtraDetailsTvApi: ExtraDetailsTvApi
):TvExtraDetailsRepo{
    override suspend fun getTvDetails(id: Int): Flow<Response<TvDetails>> {
        return flow {
            try {
                val data = tvExtraDetailsTvApi.getTvDetails(id)
                    emit(Response.Success(data = data))
            }catch (e:Exception){
                emit(Response.Error(e.message))
            }catch (e:HttpException){
                emit(Response.Error(e.message))
            }
        }
    }

    override suspend fun getTvVideo(id: Int): Flow<Response<List<TvVideos>>> {
        return flow {
            try {
                val data = tvExtraDetailsTvApi.getSeriesVideo(id).results
                    .map {
                        it.toTvVideo()
                    }
                emit(Response.Success(data = data))
            }catch (e:Exception){
                emit(Response.Error(e.message))
            }catch (e:HttpException){
                emit(Response.Error(e.message))
            }
        }
    }

    override suspend fun getTvCastAndCrew(id: Int): Flow<Response<CastCrew>> {
        return flow {
            try {
                val data = tvExtraDetailsTvApi.getTvCastCrew(id)
                emit(Response.Success(data = data))
            }catch (e:Exception){
                emit(Response.Error(e.message))
            }catch (e:HttpException){
                emit(Response.Error(e.message))
            }
        }
    }

    override suspend fun getTvSimilar(id: Int): Flow<Response<TvSimilar>> {
        return flow {
            try {
                val data = tvExtraDetailsTvApi.getTvSimilar(id)
                emit(Response.Success(data = data))
            }catch (e:Exception){
                emit(Response.Error(e.message))
            }catch (e:HttpException){
                emit(Response.Error(e.message))
            }
        }
    }
}