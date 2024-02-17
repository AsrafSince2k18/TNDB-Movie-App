package com.example.movieshow.root.data.repository.movieDetailsRepoImpl

import com.example.movieshow.root.data.maper.toMovie
import com.example.movieshow.root.data.maper.toMovieEntity
import com.example.movieshow.root.data.maper.toTvMapper
import com.example.movieshow.root.data.movieDetails.remote.api.MovieDiscoverTvApi
import com.example.movieshow.root.domain.model.Movie
import com.example.movieshow.root.domain.model.Tv
import com.example.movieshow.root.domain.repository.detailsRepo.DiscoverRepo
import com.example.movieshow.root.response.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DiscoverRepoImpl(
    private val movieDiscoverTvApi: MovieDiscoverTvApi,
) : DiscoverRepo {
    override suspend fun discoverMovie(category: String, page: Int): Flow<Response<List<Movie>>> {
        return flow {
            try {
                val fetchApi = movieDiscoverTvApi.getDiscoverMovie(
                    category=category,page=page
                )
                val movieEntity = fetchApi.results.map {
                    it.toMovieEntity(category=category)
                }
                emit(Response.Success(movieEntity.map {
                    it.toMovie(category=category)
                }))

            }catch (e:Exception){
                emit(Response.Error(e.message))
            }
            catch (e:Exception){
                emit(Response.Error(e.message))
            }
        }
    }

    override suspend fun discoverTv(category: String, page: Int): Flow<Response<List<Tv>>> {
        return flow {
            try {
                val fetchApi = movieDiscoverTvApi.getDiscoverTv(
                    category=category,page=page
                )
                val tvEntity = fetchApi.results.map {
                    it.toTvMapper()
                }

                emit(Response.Success(data = tvEntity))

            }catch (e:Exception){
                emit(Response.Error(e.message))
            }
            catch (e:Exception){
                emit(Response.Error(e.message))
            }
        }
    }

}