package com.example.movieshow.ui.presentance.onBoard.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieshow.root.domain.onBoardUseCase.ReadOnBoard
import com.example.movieshow.ui.presentance.navGraph.screenRoot.ScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ReadViewModel @Inject constructor(
    private val readOnBoard: ReadOnBoard
): ViewModel(){

    var splashScreen by mutableStateOf(true)

    var screen by mutableStateOf(ScreenRoute.OnBoardScreen.route)

    init {
        readOnBoard.invoke().onEach {screens->
            screen = if(screens){
                ScreenRoute.OnMainScreen.route
            }else{
                ScreenRoute.OnBoardScreen.route
            }
            delay(1000)
            splashScreen=false
        }.launchIn(viewModelScope)
    }

}