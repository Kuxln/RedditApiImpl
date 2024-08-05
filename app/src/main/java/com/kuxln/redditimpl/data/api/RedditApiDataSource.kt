package com.kuxln.redditimpl.data.api

import com.kuxln.redditimpl.data.RedditDataEntity
import com.kuxln.redditimpl.data.RedditDataPage
import java.util.Date
import javax.inject.Inject

class RedditApiDataSource @Inject constructor(
    private val api: RedditApiService
) {
    suspend fun getData(): RedditDataPage {
        val response = api.getTop().body()
        response?.data?.children?.let {
            return RedditDataPage(
                it.map { item ->
                    RedditDataEntity(
                        title = item.data.title,
                        author = item.data.author,
                        imageUrl = item.data.url_overridden_by_dest,
                        created = Date(item.data.created.toLong())
                    )
                }
            )
        }
        return RedditDataPage()
    }
}