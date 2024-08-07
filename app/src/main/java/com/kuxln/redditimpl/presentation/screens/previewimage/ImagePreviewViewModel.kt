package com.kuxln.redditimpl.presentation.screens.previewimage

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuxln.redditimpl.data.GalleryStorageManager

class ImagePreviewViewModel : ViewModel() {

    val liveData: LiveData<String> get() = _liveData
    private val _liveData = MutableLiveData<String>()

    private lateinit var state: String

    fun onSaveImage(context: Context, imageDrawable: Drawable) {
        saveImage(context, imageDrawable as BitmapDrawable)
    }

    fun onFragmentCreated(imageUrl: String) {
        state = imageUrl
        _liveData.postValue(state)
    }

    private fun saveImage(context: Context, imageDrawable: BitmapDrawable) {
        GalleryStorageManager.saveImage(context, imageDrawable)
    }
}