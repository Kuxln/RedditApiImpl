package com.kuxln.redditimpl.presentation.screens.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.kuxln.redditimpl.data.RedditDataEntity
import com.kuxln.redditimpl.databinding.ListItemRedditPostCardBinding

class RedditPostsDiffCallback(
    private val oldDataSet: List<RedditDataEntity>,
    private val newDataSet: List<RedditDataEntity>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldDataSet.size

    override fun getNewListSize(): Int = newDataSet.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldDataSet[oldItemPosition].author == newDataSet[newItemPosition].author &&
                oldDataSet[oldItemPosition].title == newDataSet[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldDataSet[oldItemPosition].author == newDataSet[newItemPosition].author &&
                oldDataSet[oldItemPosition].title == newDataSet[newItemPosition].title &&
                oldDataSet[oldItemPosition].created == newDataSet[newItemPosition].created
    }
}

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
    private var dataSet: List<RedditDataEntity> = emptyList()
) : RecyclerView.Adapter<RedditPostViewHolder>() {

    fun updateData(newData: List<RedditDataEntity>) {
        val diffCallback = RedditPostsDiffCallback(dataSet, newData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        dataSet = newData
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditPostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemRedditPostCardBinding.inflate(inflater, parent, false)

        return RedditPostViewHolder(binding)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: RedditPostViewHolder, position: Int) {
        val timeMetadata = getTimeMetadata(dataSet[position].created)
        val commentsCountMetadata = getCommentsCountMetadata(dataSet[position].commentsCount)

        with(holder) {
            username.text = dataSet[position].author
            title.text = dataSet[position].title
            time.text = timeMetadata
            commentsCount.text = commentsCountMetadata
            //TODO
            try {
                image.load(dataSet[position].imageUrl)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

//    override fun getItemViewType(position: Int): Int {
//        return
//    }

    private fun getTimeMetadata(timeCreated: Long): String {
        val differenceInTime = (System.currentTimeMillis() / 1000) - timeCreated
        return if (differenceInTime / 60 < 60) {
            "${differenceInTime / 60} minutes ago"
        } else if (differenceInTime / 3600 < 24) {
            "${differenceInTime / 3600} hours ago"
        } else {
            "${differenceInTime / (3600 * 24)} days ago"
        }
    }

    @SuppressLint("DefaultLocale")
    private fun getCommentsCountMetadata(commentsCount: Int): String {
        return if (commentsCount < 1000) {
            commentsCount.toString()
        } else if (commentsCount / 1000 < 10) {
            String.format("%.1fK", (commentsCount.toDouble() / 1000))
        } else {
            "${commentsCount / 1000}K"
        }
    }

    companion object {
        private const val ITEM_PROGRESS_BAR = 1
        private const val ITEM_POST = 0
    }
}