package com.example.movie.ui.presentance.onBoard.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieshow.root.domain.onBoardUseCase.SaveOnBoard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveViewModel @Inject constructor(
    private val saveOnBoard: SaveOnBoard
) : ViewModel(){

    fun saveOnBoardScreen(){
        viewModelScope.launch {
            saveOnBoard()
        }
    }

}