package com.kuxln.redditimpl.domain

import com.kuxln.redditimpl.data.RedditData
import com.kuxln.redditimpl.data.api.RedditApiDataSource
import javax.inject.Inject

class RedditRepo @Inject constructor(
    private val api: RedditApiDataSource
) {

    suspend fun getTopPosts(currentSlice: String? = null): Result<RedditData> {
        return api.getTopPosts(currentSlice)
    }

//    suspend fun getNextData(): Result<List<RedditDataEntity>> {
//        return api.getNextData()
//    }
}