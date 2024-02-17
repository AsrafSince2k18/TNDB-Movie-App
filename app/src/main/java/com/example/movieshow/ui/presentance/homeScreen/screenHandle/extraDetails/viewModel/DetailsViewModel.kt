package com.example.movieshow.ui.presentance.homeScreen.screenHandle.extraDetails.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieshow.root.domain.repository.extraDetailsRepo.MovieExtraDetailsRepo
import com.example.movieshow.root.response.Response
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.extraDetails.stateEvent.DetailsEvent
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.extraDetails.stateEvent.DetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val movieExtraDetailsRepo: MovieExtraDetailsRepo,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _state = MutableStateFlow(DetailsState())
    val state = _state.asStateFlow()

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.CurrentId -> {
                getVideo(event.currentId).apply {
                    getDetails(event.currentId)
                    getCastAndCrew(event.currentId)
                    getSimilar(event.currentId)
                }
            }

            is DetailsEvent.GetDetails -> {
                state.value.currentId?.let {
                    getVideo(it)
                    getDetails(it)
                }
            }

            is DetailsEvent.VideoClick -> {
                _state.update {
                    it.copy(
                        videoClick = event.click
                    )
                }
            }
        }
    }


    fun getDetails(id: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            _state.update {
                it.copy(isLoading = true)
            }
            movieExtraDetailsRepo.getDetails(id = id)
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    when (response) {
                        is Response.Error -> {
                            response.message?.let {
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        isError = null
                                    )
                                }
                            }
                        }

                        is Response.Success -> {
                            response.data?.let { movieDetails ->
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        isError = null,
                                        movieDetails = movieDetails
                                    )
                                }
                            }
                        }
                    }
                }
        }
    }

    fun getVideo(id : Int){
        viewModelScope.launch(Dispatchers.Main) {
            _state.update {
                it.copy(isLoading = true)
            }

            movieExtraDetailsRepo.getVideo(id=id)
                .flowOn(Dispatchers.IO)
                .collect{response->
                    when(response){
                        is Response.Error -> {
                            response.message?.let {
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        isError = state.value.isError,
                                        videoList = emptyList()
                                    )
                                }
                            }
                        }
                        is Response.Success -> {
                            response.data?.let {video->
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        isError = null,
                                        videoList = video
                                    )
                                }
                            }
                        }
                    }
                }
        }
    }

    fun getCastAndCrew(id:Int){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            movieExtraDetailsRepo.getCastAndCrew(id=id)
                .flowOn(Dispatchers.IO)
                .collect{response->
                    when(response){
                        is Response.Error -> {
                            response.message?.let {movieCast->
                                _state.update {
                                    it.copy(isLoading = false,
                                        isError = movieCast
                                    )
                                }
                            }
                        }
                        is Response.Success -> {
                            response.data?.let {movieCast->
                                _state.update {
                                    it.copy(isLoading = false,
                                        isError = null,
                                        castAndCrew = movieCast)
                                }
                            }
                        }
                    }
                }
        }
    }


    fun getSimilar(id:Int){
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            movieExtraDetailsRepo.getSimilar(id=id)
                .flowOn(Dispatchers.IO)
                .collect{response->
                    when(response){
                        is Response.Error -> {
                            response.message?.let {similar->
                                _state.update {
                                    it.copy(isLoading = false,
                                        isError = similar
                                    )
                                }
                            }
                        }
                        is Response.Success -> {
                            response.data?.let {similar->
                                _state.update {
                                    it.copy(isLoading = false,
                                        isError = null,
                                        similar = similar)
                                }
                            }
                        }
                    }
                }
        }
    }

}
