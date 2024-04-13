package com.akv.pixlock.ui.homescreen.viewmodels

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

class MediaScreenViewModel : ViewModel() {

    private val _mediaList = MutableStateFlow<List<Uri>>(emptyList())
    val mediaList get() = _mediaList

    fun updateMedia(context: Context) {

        viewModelScope.launch {
            while (true) {
                delay(1000) // May be this implementation is wrong

                val media = mutableListOf<Uri>()
                val files = context.filesDir.listFiles() ?: return@launch

                files.filter { it.canRead() && it.extension == "jpg" && it.isFile }
                    .forEach { image ->
                        media.add(Uri.fromFile(image))
                    }

                _mediaList.value = media
            }
        }
    }

    fun saveMedia(context: Context, media: List<Uri>): Boolean {

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss-ms-ns")

        return try {
            media.forEach { imgUri ->
                val currentTime = LocalDateTime.now().format(formatter)
                val fileName = "$currentTime.jpg"
                val inputStream = context.contentResolver.openInputStream(imgUri) ?: return false
                val outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)

                inputStream.use { input ->
                    outputStream.use { output ->
                        input.copyTo(output)
                    }
                }
                inputStream.close()
                outputStream.flush()
                outputStream.close()
            }

            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }
}



