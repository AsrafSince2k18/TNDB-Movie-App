package com.example.movieshow.root.domain.onBoardUseCase

import com.example.movieshow.root.domain.onBoardRepo.OnBoardRepo

class ReadOnBoard (
    private val onBoardRepo: OnBoardRepo
){
    operator fun invoke() = onBoardRepo.readOnBoard()
}