package com.example.movieshow.root.di.databaseModule

import android.content.Context
import androidx.room.Room
import com.example.movieshow.root.data.local.movieEntity.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MovieDatabase {
        return Room.databaseBuilder(context,MovieDatabase::class.java,"move_TNDB")
            .build()
    }

}