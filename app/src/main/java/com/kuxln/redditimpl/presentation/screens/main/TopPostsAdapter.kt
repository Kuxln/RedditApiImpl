package com.kuxln.redditimpl.presentation.screens.main

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kuxln.redditimpl.data.RedditDataEntity
import com.kuxln.redditimpl.databinding.ListItemRedditPostCardBinding

class RedditPostViewHolder(
    binding: ListItemRedditPostCardBinding
) : ViewHolder(binding.root) {
    var username = binding.tvUserName
    var title = binding.tvPostTitle
    var time = binding.tvTime
    var commentsCount = binding.tvCommentsSection
    var image = binding.ivPostImage
}

class RedditTopPostsAdapter(
    private val dataSet: List<RedditDataEntity>
) : RecyclerView.Adapter<RedditPostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditPostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemRedditPostCardBinding.inflate(inflater, parent, false)

        return RedditPostViewHolder(binding)
    }

    override fun getItemCount(): Int = dataSet.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RedditPostViewHolder, position: Int) {
        val differenceInTime = (System.currentTimeMillis() / 1000) - dataSet[position].created
        val timeMetadata = if (differenceInTime / 60 < 60)  {
            "${differenceInTime / 60} minutes ago"
        } else if (differenceInTime / 3600 < 24) {
            "${differenceInTime / 3600} hours ago"
        } else {
            "${differenceInTime / (3600 * 24)} days ago"
        }

        with(holder) {
            username.text = dataSet[position].author
            title.text = dataSet[position].title
            time.text = timeMetadata
            commentsCount.text = dataSet[position].commentsCount.toString()
        }
    }
}