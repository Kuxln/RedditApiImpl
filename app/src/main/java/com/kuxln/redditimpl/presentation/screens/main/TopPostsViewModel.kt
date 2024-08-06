package com.kuxln.redditimpl.presentation.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuxln.redditimpl.domain.RedditRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class TopPostsViewModel @Inject constructor(
    private val repo: RedditRepo
) : ViewModel() {

    init {
        fetchData()
    }

    val liveData: LiveData<TopPostViewState> get() = _liveData

    private val _liveData = MutableLiveData<TopPostViewState>()
    private val state = TopPostViewState()

    fun onError() {
        state.isError = null
        _liveData.postValue(state)
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getData().onSuccess {
                state.data = it
            }.onFailure {
                it.printStackTrace()
                state.isError = true
            }
            _liveData.postValue(state)
        }
    }

    private fun fetchNext() {

    }
}