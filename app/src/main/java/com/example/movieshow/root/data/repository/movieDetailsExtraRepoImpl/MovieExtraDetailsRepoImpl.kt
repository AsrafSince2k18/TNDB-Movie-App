package com.example.movieshow.root.data.repository.movieDetailsExtraRepoImpl

import com.example.movieshow.root.data.maper.toVideo
import com.example.movieshow.root.data.movieExtraDetails.remote.api.ExtraDetailsApi
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailMovie.similar.Similar
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailMovie.castCrew.MovieCast
import com.example.movieshow.root.data.movieExtraDetails.remote.respond.extraDetailMovie.details.MovieDetails
import com.example.movieshow.root.domain.model.Video
import com.example.movieshow.root.domain.repository.extraDetailsRepo.MovieExtraDetailsRepo
import com.example.movieshow.root.response.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class MovieExtraDetailsRepoImpl(
    private val extraDetailsApi: ExtraDetailsApi,
) : MovieExtraDetailsRepo {
    override suspend fun getDetails(id: Int): Flow<Response<MovieDetails>> {
        return flow {
            try {
                val data = extraDetailsApi.movieDetails(id)
                emit(Response.Success(data))
            }catch (e:Exception){
                emit(Response.Error(e.message))
                return@flow

            }catch (e:HttpException){
                emit(Response.Error(e.message))
                return@flow

            }
        }
    }

    override suspend fun getVideo(id: Int): Flow<Response<List<Video>>> {
        return flow {
            try {
                val data = extraDetailsApi.movieVideo(id)
                    .results.map {
                        it.toVideo()
                    }
                emit(Response.Success(data=data))

            }catch (e:Exception){
                emit(Response.Error(e.message))
            }
            catch (e:HttpException){
                emit(Response.Error(e.message))
            }
        }
    }

    override suspend fun getCastAndCrew(id: Int): Flow<Response<MovieCast>> {
        return flow {
            try {
                val data = extraDetailsApi.movieCastAndCrew(movieId = id)
                emit(Response.Success(data=data))

            }catch (e:Exception){
                emit(Response.Error(e.message))
            }catch (e:HttpException){
                emit(Response.Error(e.message))
            }

        }
    }

    override suspend fun getSimilar(id: Int): Flow<Response<Similar>> {
        return flow {
            try {
                val data = extraDetailsApi.movieSimilar(id)
                emit(Response.Success(data=data))

            }catch (e:Exception){
                emit(Response.Error(e.message))
            }
            catch (e:HttpException){
                emit(Response.Error(e.message))
            }
        }
    }


}
