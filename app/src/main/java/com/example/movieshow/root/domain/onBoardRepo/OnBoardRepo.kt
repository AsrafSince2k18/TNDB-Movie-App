package com.example.movieshow.root.domain.onBoardRepo

import kotlinx.coroutines.flow.Flow

interface OnBoardRepo {

    suspend fun saveOnBoard()

    fun readOnBoard() : Flow<Boolean>

}