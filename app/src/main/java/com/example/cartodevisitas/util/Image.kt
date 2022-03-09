package com.example.cartodevisitas.util

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.cartodevisitas.R
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.lang.Exception

class Image {

    companion object {
        fun share(context: Context, card: View) {
            val bitmap = getScreenShotFromView(card)

            bitmap?.let {
                saveMediaStorage(context, bitmap)
            }
        }

        private fun saveMediaStorage(context: Context, bitmap: Bitmap) {
            val fileName = "${System.currentTimeMillis()}"

            var fos: OutputStream? = null

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                context.contentResolver?.also { resolver ->
                    val values = ContentValues().apply {
                        put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                    }

                    val imageUri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

                    fos = imageUri?.let {
                        shareIntent(context, imageUri)
                        resolver.openOutputStream(it)
                    }
                }
            } else {
                val imageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                val image = File(imageDir, fileName)
                shareIntent(context, Uri.fromFile(image))
                fos = FileOutputStream(image)

            }

            fos?.use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, it)
                Toast.makeText(context, "Imagem capturada com sucesso", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        private fun shareIntent(context: Context, imageUri: Uri) {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, imageUri)
                type = "image/jpg"
            }
            context.startActivity(
                Intent.createChooser(
                    shareIntent,
                    context.getString(R.string.label_share)
                )
            )
        }


        private fun getScreenShotFromView(card: View): Bitmap? {
            var screenshot: Bitmap? = null
            try {
                screenshot = Bitmap.createBitmap(
                    card.measuredWidth,
                    card.measuredHeight,
                    Bitmap.Config.ARGB_8888
                )
                val canvas = Canvas(screenshot)
                card.draw(canvas)
            } catch (ex: Exception) {
                Log.e("IMAGE_ERROR", ex.message.toString() )
            }

            return screenshot
        }
    }

}