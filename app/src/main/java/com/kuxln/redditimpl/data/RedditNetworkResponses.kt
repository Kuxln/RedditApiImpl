package com.kuxln.redditimpl.data

import com.google.gson.annotations.SerializedName

data class RedditApiResponse(
    @SerializedName("data")
    val data: RedditData
)

data class RedditData(
    @SerializedName("children")
    val children: List<PostItem>
)

data class PostItem(
    @SerializedName("data")
    val data: RedditDataEntity,
)

data class RedditDataEntity (
    @SerializedName("title")
    val title: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("url_overridden_by_dest")
    val imageUrl: String,
    @SerializedName("created")
    val created: Long,
)