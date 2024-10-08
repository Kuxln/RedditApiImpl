package com.kuxln.redditimpl.presentation.screens.previewimage

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
            checkPermission(
                onSuccess = { viewModel.onSaveImage(binding.imagePreview.drawable) }
            )
        }

        val imageUrl = arguments?.getString(IMAGE_URL)
        imageUrl?.let { viewModel.onFragmentCreated(imageUrl) }

        viewModel.liveData.observe(this.viewLifecycleOwner) {state ->
            if (state.imageUrl != null) {
                binding.imagePreview.load(state.imageUrl)
            }

            if (state.isImageSaved == true) {
                Toast.makeText(requireActivity(), R.string.image_saved, Toast.LENGTH_SHORT).show()
                viewModel.onSaveResultShowed()
            }

            if (state.isImageSaved == false) {
                Toast.makeText(requireActivity(), R.string.image_not_saved, Toast.LENGTH_SHORT).show()
                viewModel.onSaveResultShowed()
            }
        }
    }

    private fun checkPermission(onSuccess: () -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            viewModel.onSaveImage(binding.imagePreview.drawable)
        } else if (requireActivity().checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            onSuccess()
        } else {
            requireActivity().requestPermissions(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1
            )
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