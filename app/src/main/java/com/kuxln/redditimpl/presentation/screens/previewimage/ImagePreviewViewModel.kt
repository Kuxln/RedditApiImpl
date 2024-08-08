package com.kuxln.redditimpl.presentation.screens.previewimage

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuxln.redditimpl.data.GalleryStorageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagePreviewViewModel @Inject constructor(
    private val galleryStorageManager: GalleryStorageManager
) : ViewModel() {

    val liveData: LiveData<ImagePreviewViewState> get() = _liveData
    private val _liveData = MutableLiveData<ImagePreviewViewState>()

    private var state = ImagePreviewViewState()

    fun onFragmentCreated(imageUrl: String) {
        state.imageUrl = imageUrl
        _liveData.postValue(state)
    }

    fun onSaveImage(imageDrawable: Drawable) {
        if (imageDrawable is BitmapDrawable) {
            saveImage(imageDrawable)
        }
    }

    fun onSaveResultShowed() {
        state.isImageSaved = null
        _liveData.postValue(state)
    }

    private fun saveImage(imageDrawable: BitmapDrawable) {
        viewModelScope.launch(Dispatchers.IO) {
            state.isImageSaved = galleryStorageManager.saveImage(imageDrawable)
            _liveData.postValue(state)
        }
    }
}