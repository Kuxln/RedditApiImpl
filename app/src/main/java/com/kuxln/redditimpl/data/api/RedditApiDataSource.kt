package com.kuxln.redditimpl.data.api

import com.kuxln.redditimpl.data.RedditDataEntity
import retrofit2.Response
import javax.inject.Inject

class RedditApiDataSource @Inject constructor(
    private val api: RedditApiService
) {

    suspend fun getData(): Result<List<RedditDataEntity>> {
        return try {
            val response = api.getTop().body()
            val entities = response?.data?.children?.map { it.data } ?: throw RuntimeException()
            Result.success(entities)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}