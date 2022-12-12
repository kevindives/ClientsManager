package com.magicworld.clientsmanager.ui.utils

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.magicworld.clientsmanager.main.MainActivity
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

fun askForPermission(context: Context, activity: MainActivity) {
    if (ContextCompat.checkSelfPermission(context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(activity,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA),
            1000)

    }

}

fun saveView(view: View, context: Context) {

    val bitmap = getScreenShotFromView(view)
    if (bitmap != null) {
        saveMediaToStorage(bitmap, context)
    }
}

fun getScreenShotFromView(v: View): Bitmap? {
    var screenshot: Bitmap? = null
    try {
        // inflate screenshot object
        // with Bitmap.createBitmap it
        // requires three parameters
        // width and height of the view and
        // the background color
        screenshot = Bitmap.createBitmap(v.measuredWidth, v.measuredHeight, Bitmap.Config.ARGB_8888)
        // Now draw this bitmap on a canvas
        val canvas = Canvas(screenshot)
        v.draw(canvas)
    } catch (e: Exception) {
        Log.e("GFG", "Failed to capture screenshot because:" + e.message)
    }
    // return the bitmap
    return screenshot

}

private fun saveMediaToStorage(bitmap: Bitmap, context: Context) {

    try {

        // Generating a file name
        val filename = "${System.currentTimeMillis()}.jpg"

        // Output stream
        var fos: OutputStream? = null

        // For devices running android >= Q
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // getting the contentResolver
            context.contentResolver?.also { resolver ->

                // Content resolver will process the contentvalues
                val contentValues = ContentValues().apply {

                    // putting file information in content values
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                // Inserting the contentValues to
                // contentResolver and getting the Uri
                val imageUri: Uri? =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                // Opening an outputstream with the Uri that we got
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            // These for devices running on android < Q
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }

        fos?.use {
            // Finally writing the bitmap to the output stream that we opened
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(context, "Captured View and saved to Gallery", Toast.LENGTH_SHORT).show()
        }
    }catch (e: Exception){
        Log.e("GFG", "Failed to capture screenshot because:" + e.message)
    }

}
