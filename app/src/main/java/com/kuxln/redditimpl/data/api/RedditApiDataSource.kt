package com.kuxln.redditimpl.data.api

import com.kuxln.redditimpl.data.RedditData
import javax.inject.Inject

class RedditApiDataSource @Inject constructor(
    private val api: RedditApiService
) {

    suspend fun getTopPosts(currentSlice: String? = null): Result<RedditData> {
        return try {
            val response = api.getTop(currentSlice).body()?.data ?: throw Exception("No response from API")
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}