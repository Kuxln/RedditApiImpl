package com.kuxln.redditimpl.presentation.screens.previewimage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import coil.load
import com.kuxln.redditimpl.R
import com.kuxln.redditimpl.databinding.FragmentImagePreviewBinding
import com.kuxln.redditimpl.presentation.core.ui.BaseFragment
import com.kuxln.redditimpl.presentation.core.ui.RedditNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagePreviewFragment : BaseFragment<FragmentImagePreviewBinding>(
    R.layout.fragment_image_preview
) {

    private val viewModel: ImagePreviewViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentImagePreviewBinding.bind(view)

        binding.toolbar.setNavigationOnClickListener {
            (requireActivity() as RedditNavigation).onToolbarBack()
        }

        binding.saveButton.setOnClickListener {
            viewModel.onSaveImage()
        }

        val imageUrl = arguments?.getString(IMAGE_URL, "")
        if (imageUrl?.isNotEmpty() == true) {
            viewModel.onFragmentCreated(imageUrl)
        }

        viewModel.liveData.observe(this.viewLifecycleOwner) {stateImageUrl ->
            if (stateImageUrl != null) {
                binding.imagePreview.load(stateImageUrl)
            }
        }
    }

    companion object {
        fun newInstance(imageUrl: String): ImagePreviewFragment {
            val fragment = ImagePreviewFragment().apply {
                val args = Bundle().apply {
                    putString(IMAGE_URL, imageUrl)
                }
                arguments = args
            }
            return fragment
        }

        private const val IMAGE_URL = "IMAGE_URL"
    }
}