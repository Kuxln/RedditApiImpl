package com.kuxln.redditimpl.domain

import com.kuxln.redditimpl.data.RedditDataPage
import com.kuxln.redditimpl.data.api.RedditApiDataSource
import javax.inject.Inject

class RedditRepo @Inject constructor(
    private val api: RedditApiDataSource
) {

    suspend fun getData(): RedditDataPage {
        return api.getData()
    }
}