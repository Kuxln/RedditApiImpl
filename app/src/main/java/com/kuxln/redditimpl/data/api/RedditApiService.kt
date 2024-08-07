package com.kuxln.redditimpl.data.api

import com.kuxln.redditimpl.data.RedditApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApiService {
    @GET("/top.json")
    suspend fun getTop(
        @Query("after") sliceAfter: String? = null,
        @Query("limit") limit: String = "10"
    ): Response<RedditApiResponse>

//    @GET("/top.json")
//    suspend fun getNext(
//        @Query("after") sliceAfter: String,
//        @Query("limit") limit: String = "10"
//    ): Response<RedditApiResponse>
}