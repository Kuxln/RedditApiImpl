package com.kuxln.redditimpl.presentation.core.ui

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T>(
    layout: Int
) : Fragment(layout) where T : ViewBinding {
    protected lateinit var binding: T
}