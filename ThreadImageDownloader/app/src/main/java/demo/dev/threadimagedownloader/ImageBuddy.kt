package demo.dev.threadimagedownloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

object ImageBuddy {

    private val handler = Handler(Looper.getMainLooper())
    fun downloadImageFromUrl(url: String, onSuccess: (Bitmap) -> Unit, onError: (String) -> Unit) {
        try {
            thread {
                val connection = URL(url).openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()

                val inputStream: InputStream = connection.inputStream
                val bitmap = BitmapFactory.decodeStream(inputStream)

                if (bitmap != null) {
                    handler.post {
                        onSuccess(bitmap)
                    }
                } else {
                    handler.post{
                        onError("Error in decoding bitmap")
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            e.localizedMessage?.let {
                handler.post{
                    onError(it)
                }
            }
        }
    }
}