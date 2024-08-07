package com.kuxln.redditimpl.presentation.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kuxln.redditimpl.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(
    R.layout.activity_main
) {
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
}