package com.kuxln.redditimpl.presentation.core.di

import android.content.Context
import com.kuxln.redditimpl.data.GalleryStorageManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideGalleryStorageManager(@ApplicationContext applicationContext: Context): GalleryStorageManager {
        return GalleryStorageManager(applicationContext)
    }
}