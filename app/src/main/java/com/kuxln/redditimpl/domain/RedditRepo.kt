package com.kuxln.redditimpl.domain

import com.kuxln.redditimpl.data.api.RedditApiDataSource
import javax.inject.Inject

class RedditRepo @Inject constructor(
    private val api: RedditApiDataSource
) {

}