package com.kuxln.redditimpl.data

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast

class GalleryStorageManager private constructor() {

    companion object {
        fun saveImage(context: Context, imageDrawable: BitmapDrawable) {
            val resolver = context.contentResolver
            val images = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
            } else {
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            }

            val contentValues = ContentValues()
            contentValues.put(
                MediaStore.Images.Media.DISPLAY_NAME,
                "RedditImage${System.currentTimeMillis()}.jpeg"
            )
            contentValues.put(MediaStore.Images.Media.MIME_TYPE, "images/*")
            val uri = resolver.insert(images, contentValues)

            try {
                val bitmap = imageDrawable.bitmap
                uri?.let {
                    val outputStream = resolver.openOutputStream(it)
                    outputStream?.let { stream ->
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                        Toast.makeText(context, "Image saved", Toast.LENGTH_SHORT).show()
                        stream.close()
                    } ?: throw Exception()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "Image not saved", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
