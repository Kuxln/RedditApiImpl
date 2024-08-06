package com.kuxln.redditimpl.presentation.screens.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.kuxln.redditimpl.R
import com.kuxln.redditimpl.databinding.FragmentTopPostsBinding
import com.kuxln.redditimpl.presentation.core.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopPostsFragment : BaseFragment<FragmentTopPostsBinding>(R.layout.fragment_top_posts) {
    private val viewModel: TopPostsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentTopPostsBinding.bind(view)

        viewModel.liveData.observe(this.viewLifecycleOwner) {state ->
            state.data?.let {
                for (element in it) Log.d("RedditDataTest", Gson().toJson(element))
            }

            if (state.isError == true) {
                Toast.makeText(requireActivity(), "An error occurred", Toast.LENGTH_SHORT).show()
            }
        }
    }

}