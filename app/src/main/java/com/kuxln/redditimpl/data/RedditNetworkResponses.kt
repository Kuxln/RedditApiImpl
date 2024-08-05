package com.kuxln.redditimpl.data

data class RedditApiResponse(
    val data: RedditData
)

data class RedditData(
    val children: List<PostItem>
)

data class PostItem(
    val data: PostData,
)

data class PostData (
    val title: String,
    val author: String,
    val url_overridden_by_dest: String,
    val created: Int,
)
