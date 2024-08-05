package com.kuxln.redditimpl.data

import java.util.Date

data class RedditDataPage(
    val entitiesList: List<RedditDataEntity>? = null
)

data class RedditDataEntity(
    val title: String,
    val author: String,
    val imageUrl: String,
    val created: Date,
)