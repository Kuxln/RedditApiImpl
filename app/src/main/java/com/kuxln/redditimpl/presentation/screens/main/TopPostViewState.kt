package com.kuxln.redditimpl.presentation.screens.main

import com.kuxln.redditimpl.data.RedditDataEntity

data class TopPostViewState(
    var data: List<RedditDataEntity>? = null,
    var isError: Boolean? = null,
    var isRefreshing: Boolean? = null,
)