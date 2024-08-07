package com.kuxln.redditimpl.presentation.screens.previewimage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.net.URL

class ImagePreviewViewModel: ViewModel() {

    val liveData: LiveData<String> get() = _liveData
    private val _liveData = MutableLiveData<String>()

    private lateinit var state: String

    fun onSaveImage() {
        saveImage()
    }

    fun onFragmentCreated(imageUrl: String) {
        state = imageUrl
        _liveData.postValue(state)
    }

    private fun saveImage() {

    }
}