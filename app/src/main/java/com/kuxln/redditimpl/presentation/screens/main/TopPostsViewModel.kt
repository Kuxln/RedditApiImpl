package com.kuxln.redditimpl.presentation.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuxln.redditimpl.domain.RedditRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopPostsViewModel @Inject constructor(
    private val repo: RedditRepo
) : ViewModel() {

    val liveData: LiveData<TopPostViewState> get() = _liveData
    private val _liveData = MutableLiveData<TopPostViewState>()

    private val state = TopPostViewState()
    private var currentSlice: String? = null

    init {
        fetchData()
    }

    fun onError() {
        state.isError = null
        _liveData.postValue(state)
    }

    fun onListEndReached() {
        fetchNextData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getTopPosts().onSuccess {
                state.data = it.children.map { item ->
                    item.data
                }
                currentSlice = it.after
            }.onFailure {
                it.printStackTrace()
                state.isError = true
            }
            _liveData.postValue(state)
        }
    }

    private fun fetchNextData() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getTopPosts(currentSlice).onSuccess { result ->
                currentSlice = result.after
                state.data = buildList {
                    addAll(state.data.orEmpty())
                    addAll(result.children.map { it.data })
                }
            }.onFailure {
                it.printStackTrace()
                state.isError = true
            }
            _liveData.postValue(state)
        }
    }
}