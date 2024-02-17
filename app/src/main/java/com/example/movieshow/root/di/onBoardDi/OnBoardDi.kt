package com.example.movieshow.root.di.onBoardDi

import android.content.Context
import com.example.movieshow.root.data.repository.onBoardImpl.OnBoardRepoImpl
import com.example.movieshow.root.domain.onBoardRepo.OnBoardRepo
import com.example.movieshow.root.domain.onBoardUseCase.ReadOnBoard
import com.example.movieshow.root.domain.onBoardUseCase.SaveOnBoard
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OnBoardDi {

    @Provides
    @Singleton
    fun onBoardRepoImpl(
        @ApplicationContext context: Context
    ): OnBoardRepo {
        return OnBoardRepoImpl(context = context)
    }

    @Provides
    @Singleton
    fun saveOnBoard(onBoardRepo: OnBoardRepo) = SaveOnBoard(onBoardRepo = onBoardRepo)

    @Provides
    @Singleton
    fun readOnBoard(onBoardRepo: OnBoardRepo): ReadOnBoard {
        return ReadOnBoard(onBoardRepo = onBoardRepo)
    }

}