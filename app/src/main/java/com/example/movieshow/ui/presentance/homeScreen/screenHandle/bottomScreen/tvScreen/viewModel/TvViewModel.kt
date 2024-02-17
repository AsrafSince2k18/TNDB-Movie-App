package com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.tvScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieshow.root.domain.repository.detailsRepo.TvRepo
import com.example.movieshow.root.response.Response
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.tvScreen.stateEvent.TvEvent
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.tvScreen.stateEvent.TvState
import com.example.movieshow.ui.presentance.utils.Utils
import com.example.movieshow.ui.presentance.utils.Utils.AiringToday
import com.example.movieshow.ui.presentance.utils.Utils.Popular
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(
    private val tvRepo: TvRepo
) : ViewModel(){

    private val _tvState = MutableStateFlow(TvState())
    val tvState = _tvState.asStateFlow()

    init {
        tvTopRated()
        tvPopular()
        tvOnTheAir()
        tvAiringToday()

    }

    fun tvEvent(event:TvEvent){
        when(event){
            is TvEvent.PagingTv -> {
                when(event.page){
                    AiringToday -> tvAiringToday()
                        Utils.OnTheAir->tvOnTheAir()
                        Popular ->tvPopular()
                            else -> tvTopRated()
                }
            }
        }
    }

     fun tvTopRated(){
        viewModelScope.launch {
            tvRepo.topRated(
                Utils.EndTopRated,tvState.value.tvTopRatedPage
            ).collectLatest {tv->
                when(tv){
                    is Response.Error -> {
                        tv.message?.let {tvData->
                            _tvState.update {
                                it.copy(
                                    isLoading = false,
                                    isError = tvState.value.isError,
                                    tvTopRated = emptyList()
                                )
                            }
                        }
                    }
                    is Response.Success -> {
                        tv.data?.let {tvData->
                            _tvState.update {
                                it.copy(
                                    isLoading = false,
                                    isError = null,
                                    tvTopRated = tvState.value.tvTopRated+tvData,
                                    tvTopRatedPage = tvState.value.tvTopRatedPage+1
                                )
                            }
                        }
                    }
                }
            }
        }
    }

     fun tvPopular(){
        viewModelScope.launch {
            tvRepo.popular(
                Utils.EndPopular,tvState.value.popularPage
            ).collectLatest {tv->
                when(tv){
                    is Response.Error -> {
                        tv.message?.let {tvData->
                            _tvState.update {
                                it.copy(
                                    isLoading = false,
                                    isError = tvState.value.isError,
                                    popular = emptyList()
                                )
                            }
                        }
                    }
                    is Response.Success -> {
                        tv.data?.let {tvData->
                            _tvState.update {
                                it.copy(
                                    isLoading = false,
                                    isError = null,
                                    popular = tvState.value.popular+tvData,
                                    popularPage = tvState.value.popularPage+1
                                )
                            }
                        }
                    }
                }
            }
        }
    }

     fun tvOnTheAir(){
        viewModelScope.launch {
            tvRepo.onTheAir(
                Utils.EndOnTheAir,tvState.value.onTheAirPage
            ).collectLatest {tv->
                when(tv){
                    is Response.Error -> {
                        tv.message?.let {tvData->
                            _tvState.update {
                                it.copy(
                                    isLoading = false,
                                    isError = tvState.value.isError,
                                    onTheAir = emptyList()
                                )
                            }
                        }
                    }
                    is Response.Success -> {
                        tv.data?.let {tvData->
                            _tvState.update {
                                it.copy(
                                    isLoading = false,
                                    isError = null,
                                    onTheAir = tvState.value.onTheAir+tvData,
                                    onTheAirPage = tvState.value.onTheAirPage+1
                                )
                            }
                        }
                    }
                }
            }
        }
    }

     fun tvAiringToday(){
        viewModelScope.launch {
            tvRepo.airingToday(
                Utils.EndAiring,
                tvState.value.airingTodayPage
            ).collectLatest {tv->
                when(tv){
                    is Response.Error -> {
                        tv.message?.let {tvData->
                            _tvState.update {
                                it.copy(
                                    isLoading = false,
                                    isError = tvState.value.isError,
                                    airingToday = emptyList()
                                )
                            }
                        }
                    }
                    is Response.Success -> {
                        tv.data?.let {tvData->
                            _tvState.update {
                                it.copy(
                                    isLoading = false,
                                    isError = null,
                                    airingToday = tvState.value.airingToday+tvData,
                                    airingSlide = tvData.take(5),
                                    airingTodayPage = tvState.value.airingTodayPage+1
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}