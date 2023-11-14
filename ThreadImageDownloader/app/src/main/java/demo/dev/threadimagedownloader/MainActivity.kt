package demo.dev.threadimagedownloader

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import demo.dev.threadimagedownloader.ui.theme.ThreadImageDownloaderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThreadImageDownloaderTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ImageComponent(url = "https://images.unsplash.com/photo-1682686578601-e7851641d52c")
                }
            }
        }
    }

}

@Composable
fun ImageComponent(url: String) {
    var isLoading by remember {
        mutableStateOf(true)
    }

    var bitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }

    if (bitmap == null && isLoading) {
        ImageBuddy.downloadImageFromUrl(
            url,
            onSuccess = { loadedBitmap ->
                bitmap = loadedBitmap
                isLoading = false
            },
            onError = {
                print(it)
            },
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            bitmap?.also {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "Downloaded Image",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Fit,
                )
            }
        }
    }

}
