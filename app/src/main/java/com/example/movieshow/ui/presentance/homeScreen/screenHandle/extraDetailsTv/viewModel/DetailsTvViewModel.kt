package com.example.movieshow.ui.presentance.homeScreen.screenHandle.extraDetailsTv.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieshow.root.domain.repository.tvExtraDetailsRepo.TvExtraDetailsRepo
import com.example.movieshow.root.response.Response
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.extraDetailsTv.stateEvent.DetailsTvEvent
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.extraDetailsTv.stateEvent.DetailsTvState
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
class DetailsTvViewModel @Inject constructor(
    private val extraTvDetailsRepo: TvExtraDetailsRepo
) : ViewModel() {

    private val _state = MutableStateFlow(DetailsTvState())
    val state = _state.asStateFlow()

    fun onEvent(event: DetailsTvEvent) {
        when (event) {
            is DetailsTvEvent.CurrentId -> {
                getTvVideo(event.currentId)
                getTvDetails(event.currentId)
                getTvCastAndCrew(event.currentId)
                getTvSimilar(event.currentId)

            }

            is DetailsTvEvent.VideoClick -> {
                _state.update {
                    it.copy(
                        tvVideoClick = event.click
                    )
                }
            }
        }
    }


    fun getTvDetails(id: Int) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            extraTvDetailsRepo.getTvDetails(id = id)
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
                                        tvDetails = movieDetails
                                    )
                                }
                            }
                        }
                    }
                }
        }
    }

    fun getTvVideo(id: Int) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            extraTvDetailsRepo.getTvVideo(id = id)
                .collect { response ->
                    when (response) {
                        is Response.Error -> {
                            response.message?.let {
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        isError = state.value.isError,
                                        tvVideoList = emptyList()
                                    )
                                }
                            }
                        }

                        is Response.Success -> {
                            response.data?.let { video ->
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        isError = null,
                                        tvVideoList = video
                                    )

                                }
                            }
                        }
                    }
                }
        }
    }

    private fun getTvCastAndCrew(id: Int) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            extraTvDetailsRepo.getTvCastAndCrew(id = id)
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    when (response) {
                        is Response.Error -> {
                            response.message?.let { movieCast ->
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        isError = movieCast
                                    )
                                }
                            }
                        }

                        is Response.Success -> {
                            response.data?.let { movieCast ->
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        isError = null,
                                        castAndCrew = movieCast
                                    )
                                }
                            }
                        }
                    }
                }
        }
    }


    private fun getTvSimilar(id: Int) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            extraTvDetailsRepo.getTvSimilar(id = id)
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    when (response) {
                        is Response.Error -> {
                            response.message?.let { similar ->
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        isError = similar
                                    )
                                }
                            }
                        }

                        is Response.Success -> {
                            response.data?.let { similar ->
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        isError = null,
                                        similar = similar
                                    )
                                }
                            }
                        }
                    }
                }
        }
    }


}