package com.kuxln.redditimpl.presentation.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kuxln.redditimpl.R
import com.kuxln.redditimpl.presentation.core.ui.RedditNavigation
import com.kuxln.redditimpl.presentation.screens.previewimage.ImagePreviewFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(
    R.layout.activity_main
), RedditNavigation {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tag = TopPostsFragment::class.java.name
        if (supportFragmentManager.findFragmentByTag(tag) == null){
            val fragment = TopPostsFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, fragment, tag)
                .show(fragment)
                .commit()
        }
    }

    override fun openUrl(imageUrl: String) {
            val fragment = ImagePreviewFragment.newInstance(imageUrl)
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, fragment)
                .show(fragment)
                .commit()
    }
}