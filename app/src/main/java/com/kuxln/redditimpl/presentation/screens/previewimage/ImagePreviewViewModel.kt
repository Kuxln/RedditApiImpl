package com.kuxln.redditimpl.presentation.screens.previewimage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImagePreviewViewModel: ViewModel() {

    val liveData: LiveData<String> get() = _liveData
    private val _liveData = MutableLiveData<String>()

    private lateinit var state: String

    fun onFragmentCreated(imageUrl: String) {
        state = imageUrl
        _liveData.postValue(state)
    }
}