package com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.homeScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieshow.root.domain.repository.detailsRepo.MovieListRepo
import com.example.movieshow.root.response.Response
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.homeScreen.stateEvent.HomeEvent
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.homeScreen.stateEvent.HomeNetworkState
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
class HomeViewModel @Inject constructor(
    private val movieListRepo: MovieListRepo,
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeNetworkState())
    val homeState = _homeState.asStateFlow()


    init {
        getNowPlaying(false)
        getTrendingAll()
        getPopular(false)
        getTopRated(false)
        getUpcoming(false)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.ActiveSearchBar -> {}
            is HomeEvent.OnQueryChange -> {}
            is HomeEvent.Paging -> {
                when (event.category) {
                    Utils.nowPlaying -> getNowPlaying(true)
                    Utils.popular -> getPopular(true)
                    Utils.topRated -> getTopRated(true)
                    Utils.upComing -> getUpcoming(true)
                }
            }

            HomeEvent.PullToRefresh -> {}
        }
    }


    private fun getNowPlaying(check: Boolean) {
        viewModelScope.launch {
            _homeState.update {
                it.copy(isLoading = true)
            }
            delay(1000)
            movieListRepo.getMovieList(
                check,
                category = Utils.NOW_PLAYING,
                page = homeState.value.nowPlayingUpComingPage
            )
                .collect { movie ->
                    when (movie) {
                        is Response.Error -> {
                            movie.message?.let {
                                _homeState.update {
                                    it.copy(
                                        isLoading = false,
                                        isError = homeState.value.isError,
                                        nowPlaying = emptyList()
                                    )
                                }
                            }
                        }

                        is Response.Success -> {
                            movie.data?.let { nowPlaying ->
                                _homeState.update {
                                    it.copy(
                                        isLoading = false,
                                        isError = null,
                                        movieAutoSwipe = nowPlaying.take(7),
                                        nowPlaying = homeState.value.nowPlaying + nowPlaying,
                                        nowPlayingUpComingPage = homeState.value.nowPlayingUpComingPage + 1
                                    )
                                }
                            }
                        }
                    }
                }
        }
    }

    private fun getTrendingAll() {
        viewModelScope.launch {
            _homeState.update {
                it.copy(isLoading = true)
            }
            delay(1000)
            movieListRepo.getAllTrending(
                category = Utils.TRENDING
            )
                .collectLatest { movie ->
                    when (movie) {
                        is Response.Error -> {
                            movie.message?.let { trending ->
                                _homeState.update {
                                    it.copy(
                                        isLoading = false,
                                        isError = homeState.value.isError,
                                        allTrending = emptyList()
                                    )
                                }
                            }
                        }

                        is Response.Success -> {
                            movie.data?.let { trending ->
                                _homeState.update {
                                    it.copy(
                                        isLoading = false,
                                        isError = null,
                                        allTrending = homeState.value.allTrending+trending)
                                }
                            }
                        }

                    }
                }
        }
    }


    private fun getPopular(check: Boolean) {
        viewModelScope.launch {
            _homeState.update {
                it.copy(isLoading = true)
            }
            delay(1000)
            movieListRepo.getPopular(
                check,
                Utils.POPULAR,
                homeState.value.popularUpComingPage
            )
                .collectLatest { movie ->
                    when (movie) {
                        is Response.Error -> {
                            movie.message?.let { popular ->
                                _homeState.update {
                                    it.copy(
                                        isLoading = false,
                                        isError = movie.message ?: "",
                                        popularList = emptyList()
                                    )
                                }
                            }
                        }

                        is Response.Success -> {
                            movie.data?.let { popular ->
                                _homeState.update {
                                    it.copy(
                                        isLoading = false,
                                        isError = null,
                                        popularList = homeState.value.popularList + popular,
                                        popularUpComingPage = homeState.value.popularUpComingPage + 1
                                    )
                                }
                            }
                        }

                    }
                }
        }
    }


    private fun getTopRated(forceFetchScreen: Boolean) {
        viewModelScope.launch {
            _homeState.value = homeState.value.copy(
                isLoading = true
            )
            delay(1000)
            movieListRepo.getTopRated(
                forceFetchScreen,
                Utils.TOP_RATED,
                homeState.value.topRatedUpComingPage
            )
                .collectLatest { movie ->
                    when (movie) {
                        is Response.Error -> {
                           movie.message?.let {topRated->
                               _homeState.update {
                                   it.copy(
                                       isLoading = false,
                                       isError = topRated,
                                       topRatedList = emptyList()
                                   )
                               }
                           }
                        }

                        is Response.Success -> {
                            movie.data?.let {topRated->
                                _homeState.update {
                                    it.copy(
                                        isLoading = false,
                                        isError = null,
                                        topRatedList = homeState.value.topRatedList+topRated,
                                        topRatedUpComingPage = homeState.value.topRatedUpComingPage+1
                                    )
                                }
                            }
                        }

                    }
                }
        }
    }

    private fun getUpcoming(forceFetchScreen: Boolean) {
        viewModelScope.launch {
            _homeState.value = homeState.value.copy(
                isLoading = true
            )
            delay(1000)
            movieListRepo.getUpcoming(
                forceFetchScreen,
                Utils.UPCOMING,
                homeState.value.upComingMovieUpComingPage
            )
                .collectLatest { movie ->
                    when (movie) {
                        is Response.Error -> {
                            movie.message?.let { upComing->
                                _homeState.update {
                                    it.copy(
                                        isLoading = false,
                                        isError = upComing,
                                        upComingList = emptyList()
                                    )
                                }
                            }
                        }

                        is Response.Success -> {
                            movie.data?.let { upComing->
                                _homeState.update {
                                    it.copy(
                                        isLoading = false,
                                        isError = null,
                                        upComingList = homeState.value.upComingList+upComing,
                                        upComingMovieUpComingPage = homeState.value.upComingMovieUpComingPage+1
                                    )
                                }
                            }
                        }

                    }
                }
        }
    }

}