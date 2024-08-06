package com.kuxln.redditimpl.presentation.screens.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.gson.Gson
import com.kuxln.redditimpl.R
import com.kuxln.redditimpl.databinding.FragmentTopPostsBinding
import com.kuxln.redditimpl.presentation.core.ui.BaseFragment
import com.kuxln.redditimpl.presentation.core.ui.PaddingDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopPostsFragment : BaseFragment<FragmentTopPostsBinding>(R.layout.fragment_top_posts) {
    private val viewModel: TopPostsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentTopPostsBinding.bind(view)

        binding.recycler.visibility = View.GONE

        viewModel.liveData.observe(this.viewLifecycleOwner) { state ->
            state.data?.let { //TODO
                binding.recycler.adapter = RedditTopPostsAdapter(it)
                binding.recycler.layoutManager = LinearLayoutManager(requireActivity())
                binding.recycler.addItemDecoration(PaddingDecoration(18))

                binding.progressBar.visibility = View.GONE
                binding.recycler.visibility = View.VISIBLE
            }

            if (state.isError == true) {
                Toast.makeText(requireActivity(), "An error occurred", Toast.LENGTH_SHORT).show()
                viewModel.onError()
            }
        }
    }

}