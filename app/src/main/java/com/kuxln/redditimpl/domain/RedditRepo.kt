package com.kuxln.redditimpl.domain

import com.kuxln.redditimpl.data.RedditDataEntity
import com.kuxln.redditimpl.data.api.RedditApiDataSource
import javax.inject.Inject

class RedditRepo @Inject constructor(
    private val api: RedditApiDataSource
) {

    suspend fun getData(): Result<List<RedditDataEntity>> {
        return api.getData()
    }
}