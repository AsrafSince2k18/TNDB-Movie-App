package com.example.movieshow.root.di.networkModule

import com.example.movieshow.root.data.movieDetails.remote.api.MovieDiscoverTvApi
import com.example.movieshow.root.data.movieDetails.remote.api.MovieListApi
import com.example.movieshow.root.data.movieDetails.remote.api.SearchApi
import com.example.movieshow.root.data.movieDetails.remote.api.TvSeriesListApi
import com.example.movieshow.root.data.movieExtraDetails.remote.api.ExtraDetailsApi
import com.example.movieshow.root.data.movieExtraDetails.remote.api.ExtraDetailsTvApi
import com.example.movieshow.ui.presentance.utils.Utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    private val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun provideApi() : MovieListApi{
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieListApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDiscoverApi():MovieDiscoverTvApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieDiscoverTvApi::class.java)
    }


    @Provides
    @Singleton
    fun provideTvApi():TvSeriesListApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TvSeriesListApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSearchApi():SearchApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchApi::class.java)
    }

    @Provides
    @Singleton
    fun provideExtraDetailsApi(): ExtraDetailsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExtraDetailsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideExtraTvDetailsApi():ExtraDetailsTvApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExtraDetailsTvApi::class.java)
    }

}