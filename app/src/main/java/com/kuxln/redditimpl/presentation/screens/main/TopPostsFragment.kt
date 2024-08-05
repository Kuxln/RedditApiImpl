package com.kuxln.redditimpl.presentation.screens.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kuxln.redditimpl.R
import com.kuxln.redditimpl.databinding.TopPostsFragmentBinding
import com.kuxln.redditimpl.presentation.core.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopPostsFragment : BaseFragment<TopPostsFragmentBinding>(R.layout.top_posts_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = TopPostsFragmentBinding.bind(view)
        val viewModel: TopPostsViewModel by viewModels()

    }
}