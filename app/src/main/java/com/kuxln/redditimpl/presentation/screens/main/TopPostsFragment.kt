package com.kuxln.redditimpl.presentation.screens.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kuxln.redditimpl.R
import com.kuxln.redditimpl.databinding.FragmentTopPostsBinding
import com.kuxln.redditimpl.presentation.core.ui.BaseFragment
import com.kuxln.redditimpl.presentation.core.ui.PaddingDecoration
import com.kuxln.redditimpl.presentation.core.ui.RedditNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopPostsFragment : BaseFragment<FragmentTopPostsBinding>(R.layout.fragment_top_posts) {
    private val viewModel: TopPostsViewModel by viewModels()
    private val adapter = RedditTopPostsAdapter(
        onListEndReached = { viewModel.onListEndReached() },
        onImageClicked = { (requireActivity() as RedditNavigation).openImagePreview(it) },
        onLinkClicked = {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setData(Uri.parse(it))
            }
            startActivity(intent)
        }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentTopPostsBinding.bind(view)

        binding.recycler.layoutManager = LinearLayoutManager(requireActivity())
        binding.recycler.addItemDecoration(PaddingDecoration(18))
        binding.recycler.adapter = adapter

        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.onRefresh()
        }

        viewModel.liveData.observe(this.viewLifecycleOwner) { state ->
            state.data?.let {
                adapter.updateData(it)
                if (binding.progressBar.visibility == View.VISIBLE) {
                    binding.progressBar.visibility = View.GONE
                }
            }

            if (state.isRefreshing == false) {
                binding.swipeToRefresh.isRefreshing = false
            }

            if (state.isError == true) {
                Toast.makeText(requireActivity(), "An error occurred", Toast.LENGTH_SHORT).show()
                viewModel.onError()
            }
        }
    }
}