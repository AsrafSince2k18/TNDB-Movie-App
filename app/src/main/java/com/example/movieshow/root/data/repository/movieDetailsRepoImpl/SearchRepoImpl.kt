package com.example.movieshow.root.data.repository.movieDetailsRepoImpl

import com.example.movieshow.root.data.movieDetails.remote.api.SearchApi
import com.example.movieshow.root.data.movieDetails.remote.respond.search.SearchData
import com.example.movieshow.root.domain.repository.detailsRepo.SearchRepo
import com.example.movieshow.root.response.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRepoImpl (
    private val searchApi: SearchApi
) : SearchRepo{
    override suspend fun searchData(query: String, page: Int): Flow<Response<List<SearchData>>> {
        return flow {
            try {
                val data = searchApi.getSearch(query = query,
                    page=page).results
                emit(Response.Success(data=data))
                return@flow

            }catch (e:Exception){
                emit(Response.Error(e.message))
                return@flow

            }
            catch (e:Exception){
                emit(Response.Error(e.message))
                return@flow

            }
        }
    }
}