package com.kuxln.redditimpl.presentation.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kuxln.redditimpl.domain.RedditRepo
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class TopPostsViewModel @Inject constructor(
    repo: RedditRepo
) : ViewModel() {

    val liveData: LiveData<TopPostViewState> get() = _liveData

    private val _liveData = MutableLiveData<TopPostViewState>()
    private val state = TopPostViewState()
}