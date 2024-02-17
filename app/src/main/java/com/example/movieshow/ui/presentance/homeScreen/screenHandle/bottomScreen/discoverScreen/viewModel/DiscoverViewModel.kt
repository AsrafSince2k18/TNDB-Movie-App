package com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.discoverScreen.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieshow.root.domain.repository.detailsRepo.DiscoverRepo
import com.example.movieshow.root.response.Response
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.discoverScreen.stateEvent.DiscoverEvent
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.discoverScreen.stateEvent.DiscoverState
import com.example.movieshow.ui.presentance.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val discoverRepo: DiscoverRepo
) : ViewModel() {

    private val _state = MutableStateFlow(DiscoverState())
    val state = _state.asStateFlow()

    fun onEvent(event: DiscoverEvent) {
        when (event) {
            is DiscoverEvent.DiscoverItem -> {
                _state.update {
                    it.copy(itemSelected = event.item)
                }

                when (state.value.itemSelected) {
                    0 -> discoverMovie()
                    1 -> discoverTv()
                }

            }

            is DiscoverEvent.DiscoverMovieItem -> {
                _state.update {
                    it.copy(discoverCategory = event.category)
                }

            }


        }
    }


    private fun discoverMovie() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            delay(1000)
            discoverRepo.discoverMovie(
                Utils.DISCOVER_MOVIE,
                state.value.discoverMoviePage
            )
                .collectLatest { movie ->
                    when (movie) {
                        is Response.Error -> {
                            movie.message?.let { discover ->
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        isError = discover,
                                        discoverMovieList = emptyList(),
                                    )
                                }
                            }
                        }

                        is Response.Success -> {
                            movie.data?.let { discover ->
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        isError = null,
                                        discoverMovieList = state.value.discoverMovieList + discover,
                                        discoverMoviePage = state.value.discoverMoviePage + 1,
                                    )
                                }
                            }
                        }

                    }
                }
        }
    }

    private fun discoverTv() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            delay(1000)
            discoverRepo.discoverTv(
                Utils.DISCOVER_TV,
                state.value.discoverTvPage
            )
                .collectLatest { movie ->
                    when (movie) {
                        is Response.Error -> {
                            movie.message?.let { discover ->
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        isError = discover,
                                        discoverTvList = emptyList(),
                                    )
                                }
                            }
                        }

                        is Response.Success -> {
                            movie.data?.let { discover ->
                                _state.update {
                                    it.copy(
                                        isLoading = false,
                                        isError = null,
                                        discoverTvList = state.value.discoverTvList + discover,
                                        discoverTvPage = state.value.discoverTvPage + 1,
                                    )
                                }
                            }
                        }

                    }
                }
        }
    }

}