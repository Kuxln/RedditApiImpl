package com.kuxln.redditimpl.data.api

import com.kuxln.redditimpl.data.RedditApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface RedditApiService {
    @GET("/top.json")
    suspend fun getTop() : Response<RedditApiResponse>
}