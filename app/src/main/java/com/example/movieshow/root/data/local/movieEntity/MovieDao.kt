package com.example.movieshow.root.data.local.movieEntity

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Upsert
    suspend fun upsertMovie(movie : List<MovieEntity>)

    @Query("SELECT * FROM movie_entity WHERE category IN(:category)")
     fun getMovieList(category: String) : Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_entity WHERE id IN(:id)")
    suspend fun getMovieId(id:Int) : MovieEntity

}