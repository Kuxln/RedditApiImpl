package com.kuxln.redditimpl.presentation.screens.previewimage

import android.os.Bundle
import android.view.View
import coil.load
import com.kuxln.redditimpl.R
import com.kuxln.redditimpl.databinding.FragmentImagePreviewBinding
import com.kuxln.redditimpl.presentation.core.ui.BaseFragment

class ImagePreviewFragment : BaseFragment<FragmentImagePreviewBinding>(
    R.layout.fragment_image_preview
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentImagePreviewBinding.bind(view)

        //TODO("Change logic)
        val imageUrl = arguments?.getString(IMAGE_URL, "")
        binding.imagePreview.load(imageUrl)
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