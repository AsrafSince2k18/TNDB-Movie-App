package com.example.movieshow.root.di.movieModule

import com.example.movieshow.root.data.local.movieEntity.MovieDatabase
import com.example.movieshow.root.data.movieDetails.remote.api.MovieDiscoverTvApi
import com.example.movieshow.root.data.movieDetails.remote.api.MovieListApi
import com.example.movieshow.root.data.movieDetails.remote.api.SearchApi
import com.example.movieshow.root.data.movieDetails.remote.api.TvSeriesListApi
import com.example.movieshow.root.data.movieExtraDetails.remote.api.ExtraDetailsApi
import com.example.movieshow.root.data.movieExtraDetails.remote.api.ExtraDetailsTvApi
import com.example.movieshow.root.data.repository.movieDetailsExtraRepoImpl.MovieExtraDetailsRepoImpl
import com.example.movieshow.root.data.repository.movieDetailsRepoImpl.DiscoverRepoImpl
import com.example.movieshow.root.data.repository.movieDetailsRepoImpl.MovieDetailsRepoImpl
import com.example.movieshow.root.data.repository.movieDetailsRepoImpl.SearchRepoImpl
import com.example.movieshow.root.data.repository.movieDetailsRepoImpl.TvRepoImpl
import com.example.movieshow.root.data.repository.tvExtraDetailsRepoImpl.TvExtraDetailsRepoImpl
import com.example.movieshow.root.domain.repository.detailsRepo.DiscoverRepo
import com.example.movieshow.root.domain.repository.detailsRepo.MovieListRepo
import com.example.movieshow.root.domain.repository.detailsRepo.SearchRepo
import com.example.movieshow.root.domain.repository.detailsRepo.TvRepo
import com.example.movieshow.root.domain.repository.extraDetailsRepo.MovieExtraDetailsRepo
import com.example.movieshow.root.domain.repository.tvExtraDetailsRepo.TvExtraDetailsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {


    @Provides
    @Singleton
    fun provideRepository(
        movieListApi: MovieListApi,
        movieDatabase: MovieDatabase
    ):MovieListRepo{
        return MovieDetailsRepoImpl(
            movieListApi, movieDatabase
        )
    }

    @Provides
    @Singleton
    fun provideDiscover(
        movieDiscoverTvApi : MovieDiscoverTvApi
    ):DiscoverRepo{
        return DiscoverRepoImpl(movieDiscoverTvApi=movieDiscoverTvApi)
    }

    @Provides
    @Singleton
    fun provideTvRepoImpl(
        tvSeriesListApi: TvSeriesListApi
    ):TvRepo{
        return TvRepoImpl(tvSeriesListApi=tvSeriesListApi)
    }

    @Provides
    @Singleton
    fun provideSearchRepoImpl(
        searchApi: SearchApi
    ):SearchRepo{
        return SearchRepoImpl(searchApi=searchApi)
    }

    @Provides
    @Singleton
    fun provideExtraRepoImpl(
        extraDetailsApi: ExtraDetailsApi,
    ):MovieExtraDetailsRepo{
        return MovieExtraDetailsRepoImpl(extraDetailsApi=extraDetailsApi)
    }

    @Provides
    @Singleton
    fun provideTvExtraRepoImpl(
       extraDetailsTvApi: ExtraDetailsTvApi,
    ):TvExtraDetailsRepo{
        return TvExtraDetailsRepoImpl(tvExtraDetailsTvApi =extraDetailsTvApi)
    }

}