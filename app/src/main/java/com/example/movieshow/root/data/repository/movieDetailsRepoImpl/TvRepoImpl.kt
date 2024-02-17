package com.example.movieshow.root.data.repository.movieDetailsRepoImpl

import com.example.movieshow.root.data.maper.toTvMapper
import com.example.movieshow.root.data.movieDetails.remote.api.TvSeriesListApi
import com.example.movieshow.root.domain.model.Tv
import com.example.movieshow.root.domain.repository.detailsRepo.TvRepo
import com.example.movieshow.root.response.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class TvRepoImpl (
    private val tvSeriesListApi: TvSeriesListApi
) : TvRepo{

    override suspend fun topRated(category: String, page: Int): Flow<Response<List<Tv>>> {
        return flow {
            try {
                val fetchApi = tvSeriesListApi.topRated(category=category,page=page)
                emit(Response.Success(fetchApi.results.map {
                    it.toTvMapper()
                }))
                return@flow
            }catch (e:Exception){
                emit(Response.Error(e.message))
                return@flow
            }
            catch (e:HttpException){
                emit(Response.Error(e.message))
                return@flow
            }
        }
    }

    override suspend fun airingToday(category: String, page: Int): Flow<Response<List<Tv>>> {
       return flow {
           try {
               val fetchApi = tvSeriesListApi.airingToday(
                   category=category,page =page
               ).results
               emit(Response.Success(fetchApi.map {toTvSeries->
                   toTvSeries.toTvMapper()
               }))
               return@flow
           }catch (e:Exception){
               emit(Response.Error(e.message))
               return@flow
           }catch (e:Exception){
               emit(Response.Error(e.message))
               return@flow
           }

       }
    }

    override suspend fun onTheAir(category: String, page: Int): Flow<Response<List<Tv>>> {
        return flow {
            try {
                val fetchApi = tvSeriesListApi.onTheAir(
                    category=category,page =page
                ).results
                emit(Response.Success(fetchApi.map {toTvSeries->
                    toTvSeries.toTvMapper()
                }))
                return@flow
            }catch (e:Exception){
                emit(Response.Error(e.message))
                return@flow
            }catch (e:Exception){
                emit(Response.Error(e.message))
                return@flow
            }

        }
    }

    override suspend fun popular(category: String, page: Int):Flow<Response<List<Tv>>> {
        return flow {
            try {
                val fetchApi = tvSeriesListApi.popular(
                    category=category,page =page
                ).results
                emit(Response.Success(fetchApi.map {toTvSeries->
                    toTvSeries.toTvMapper()
                }))
                return@flow
            }catch (e:Exception){
                emit(Response.Error(e.message))
                return@flow
            }catch (e:Exception){
                emit(Response.Error(e.message))
                return@flow
            }

        }
    }
}