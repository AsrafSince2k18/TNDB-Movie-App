package com.example.movieshow.root.data.repository.movieDetailsRepoImpl

import com.example.movieshow.root.data.local.movieEntity.MovieDatabase
import com.example.movieshow.root.data.maper.toMovie
import com.example.movieshow.root.data.maper.toMovieEntity
import com.example.movieshow.root.data.movieDetails.remote.api.MovieListApi
import com.example.movieshow.root.domain.model.Movie
import com.example.movieshow.root.domain.repository.detailsRepo.MovieListRepo
import com.example.movieshow.root.response.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class MovieDetailsRepoImpl(
    private val movieListApi: MovieListApi,
    private val movieDatabase: MovieDatabase
) : MovieListRepo {

    override suspend fun getMovieList(forceFetchScreen : Boolean,category: String, page: Int)
            : Flow<Response<List<Movie>>> {
        return flow {
            val movieList = movieDatabase.movieDao().getMovieList(category = category).firstOrNull().orEmpty()
            if (movieList.isNotEmpty() && !forceFetchScreen) {
                emit(Response.Success(
                    data = movieList.map { movieEntity ->
                        movieEntity.toMovie(category = category)
                    }
                ))
                return@flow
            }

            val fetchApi = try {
                movieListApi.getMovieList(category, page = page)
            } catch (e: Exception) {
                emit(Response.Error(e.message))
                return@flow
            } catch (e: HttpException) {
                emit(Response.Error(e.message))
                return@flow
            }

            val movieEntity = fetchApi.results.map {
                it.toMovieEntity(category = category)
            }
            movieDatabase.movieDao().upsertMovie(movieEntity)
            emit(
                Response.Success(
                    data = movieEntity.map { it.toMovie(category = category) })
            )

        }

    }

    override suspend fun getAllTrending(category: String): Flow<Response<List<Movie>>> {
        return flow {

            val movieList = movieDatabase.movieDao().getMovieList(category = category).firstOrNull().orEmpty()

            if (movieList.isNotEmpty()) {
                emit(Response.Success(
                    data = movieList.map { movieEntity ->
                        movieEntity.toMovie(category = category)
                    }
                ))
                return@flow
            }

            val fetchApi = try {
                movieListApi.getAllTrending(category = category)
            } catch (e: Exception) {
                emit(Response.Error(e.message))
                return@flow
            } catch (e: HttpException) {
                emit(Response.Error(e.message))
                return@flow
            }
            val movieEntity = fetchApi.results.map {
                it.toMovieEntity(category = category)
            }
            movieDatabase.movieDao().upsertMovie(movieEntity)
            emit(Response.Success(
                data = movieEntity.map {
                    it.toMovie(category)
                }
            ))
        }
    }



    override suspend fun getPopular(forceFetchScreen : Boolean
                                    ,category: String, page: Int,
                                    ): Flow<Response<List<Movie>>> {
        return flow {


            val movieEntity = movieDatabase.movieDao().getMovieList(category = category).firstOrNull().orEmpty()

            val shouldLocalMovie = movieEntity.isNotEmpty() && !forceFetchScreen
            if (shouldLocalMovie) {
                emit(Response.Success(data = movieEntity.map { entity ->
                    entity.toMovie(category = category)
                }))
                return@flow
            }

            val fetchApi = try {
                movieListApi.getPopular(category = category,page=page)
            }catch (e:Exception){
                emit(Response.Error(e.message))
                return@flow
            }
            catch (e:HttpException){
                emit(Response.Error(e.message))
                return@flow
            }


            val movieInsert = fetchApi.results.map { trasform->
                trasform.toMovieEntity(category=category)
            }

            movieDatabase.movieDao().upsertMovie(movieInsert)
           emit(Response.Success(
                data = movieInsert.map {
                    it.toMovie(category=category)
                }
            ))
        }
    }

    override suspend fun getTopRated(forceFetchScreen : Boolean,category: String, page: Int): Flow<Response<List<Movie>>> {
        return flow {
            val movieEntity = movieDatabase.movieDao().getMovieList(category=category).firstOrNull().orEmpty()
            val shouldLocalMovie = movieEntity.isNotEmpty() && !forceFetchScreen

            if(shouldLocalMovie){
                emit(Response.Success(
                    data = movieEntity.map {toMovieEntity ->
                        toMovieEntity.toMovie(category=category)
                    }
                ))
                return@flow
            }
            val fetchApi = try {
                movieListApi.getTopRated(category=category,page=page)

            }catch (e:Exception){
                emit(Response.Error(e.message))
                return@flow
            }
            catch (e:HttpException){
                emit(Response.Error(e.message))
                return@flow
            }

            val movieList = fetchApi.results.map {
                it.toMovieEntity(category=category)
            }
            movieDatabase.movieDao().upsertMovie(movieList)
            emit(Response.Success(
                data=movieList.map {
                    it.toMovie(category=category)
                }
            ))


        }
    }

    override suspend fun getUpcoming(forceFetchScreen : Boolean,category: String, page: Int): Flow<Response<List<Movie>>> {
        return flow {
            val getMovieCategory = movieDatabase.movieDao().getMovieList(category=category).firstOrNull().orEmpty()

            if(getMovieCategory.isNotEmpty() && !forceFetchScreen){
                emit(Response.Success(
                    data = getMovieCategory.map {
                        it.toMovie(category=category)
                    }
                ))
            }

            val fetchApi = try {
                movieListApi.getUpComing(category=category,page=page)
            }catch (e:Exception){
                emit(Response.Error(e.message))
                return@flow
            }catch (e:HttpException){
                emit(Response.Error(e.message))
                return@flow
            }

            val insertMovie = fetchApi.results.map {
                it.toMovieEntity(category=category)
            }
            movieDatabase.movieDao().upsertMovie(insertMovie)
            emit(Response.Success(
                data = insertMovie.map {
                    it.toMovie(category=category)
                }
            ))

        }
    }

}