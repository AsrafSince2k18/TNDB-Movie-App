package com.example.movieshow.root.domain.onBoardUseCase

import com.example.movieshow.root.domain.onBoardRepo.OnBoardRepo

class SaveOnBoard(
    private val onBoardRepo: OnBoardRepo
) {

    suspend operator fun invoke() = onBoardRepo.saveOnBoard()

}