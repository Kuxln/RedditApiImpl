package com.kuxln.redditimpl.presentation.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuxln.redditimpl.domain.RedditRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                state.dataPages = listOf(repo.getData())
                _liveData.postValue(state)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun fetchNext() {

    }
}