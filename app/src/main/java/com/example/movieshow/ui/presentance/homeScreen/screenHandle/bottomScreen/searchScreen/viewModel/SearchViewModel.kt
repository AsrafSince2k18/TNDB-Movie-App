package com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.searchScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieshow.root.domain.repository.detailsRepo.SearchRepo
import com.example.movieshow.root.response.Response
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.searchScreen.stateEvent.SearchEvent
import com.example.movieshow.ui.presentance.homeScreen.screenHandle.bottomScreen.searchScreen.stateEvent.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepo: SearchRepo
) : ViewModel() {

    private val _searchState = MutableStateFlow(SearchState())
    val searchState = _searchState.asStateFlow()

    fun searchEvent(event :SearchEvent){
        when(event){
            is SearchEvent.OnValueChange -> {
                _searchState.update {
                    it.copy(query = event.query)
                }
                    searchMovie(event.query)
            }

            SearchEvent.OnSearch -> {
                searchState.value.query.let {
                    searchMovie(it)
                }
            }

            is SearchEvent.SearchActive -> {
                _searchState.update {
                    it.copy(searchBarActive = event.active)
                }
            }

            is SearchEvent.Paginate -> {
                _searchState.update {
                    it.copy(searchPage = event.page)
                }
            }
        }
    }


    private fun searchMovie(query:String){

        viewModelScope.launch {
            _searchState.update {
                it.copy(isLoading = true)
            }
            delay(1000)

            searchRepo.searchData(
               query,
                searchState.value.searchPage
            ).flowOn(Dispatchers.IO)
                .collect {searchData->
                when(searchData){
                    is Response.Error -> {
                        searchData.message?.let {search->
                            _searchState.update {
                                it.copy(
                                    isLoading = false,
                                    isError = search,
                                    searchListData = emptyList()
                                )
                            }
                        }
                    }
                    is Response.Success -> {
                        searchData.data?.let {search->
                            _searchState.update {
                                it.copy(
                                    isLoading = false,
                                    isError = null,
                                    searchListData =search,
                                    searchPage = searchState.value.searchPage+1
                                )
                            }
                        }
                    }
                }
            }
        }

    }

}