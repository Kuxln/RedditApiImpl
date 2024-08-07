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

//    suspend fun getNextData(): Result<List<RedditDataEntity>> {
//        return try {
//            val response = api.getNext(currentSlice).body()
//            currentSlice = response?.data?.after.orEmpty()
//            val entities = response?.data?.children?.map { it.data } ?: throw RuntimeException()
//            Result.success(entities)
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
//    }
}