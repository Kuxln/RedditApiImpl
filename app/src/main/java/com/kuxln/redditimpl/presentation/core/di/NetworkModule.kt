package com.kuxln.redditimpl.presentation.core.di

import com.kuxln.redditimpl.data.api.RedditApiDataSource
import com.kuxln.redditimpl.data.api.RedditApiService
import com.kuxln.redditimpl.domain.RedditRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.reddit.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRedditApiService(retrofit: Retrofit): RedditApiService {
        return retrofit.create(RedditApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRedditApiDataSource(apiService: RedditApiService): RedditApiDataSource {
        return RedditApiDataSource(apiService)
    }

    @Provides
    @Singleton
    fun provideRedditRepo(api: RedditApiDataSource): RedditRepo {
        return RedditRepo(api)
    }
}