package com.kuxln.redditimpl.presentation.screens.previewimage

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuxln.redditimpl.data.GalleryStorageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImagePreviewViewModel @Inject constructor(
    private val galleryStorageManager: GalleryStorageManager
) : ViewModel() {

    val liveData: LiveData<String> get() = _liveData
    private val _liveData = MutableLiveData<String>()

    private lateinit var state: String

    fun onSaveImage(imageDrawable: Drawable) {
        saveImage(imageDrawable as BitmapDrawable)
    }

    fun onFragmentCreated(imageUrl: String) {
        state = imageUrl
        _liveData.postValue(state)
    }

    private fun saveImage(imageDrawable: BitmapDrawable) {
        galleryStorageManager.saveImage(imageDrawable)
    }
}