package demo.dev.servicetest

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import demo.dev.servicetest.ui.theme.ServiceTestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {

    val context = LocalContext.current
    val intent = Intent(context, MyService::class.java)

    Scaffold(
    ) { paddingValues ->
        val padding = paddingValues

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            ElevatedButton(onClick = {

                context.startService(intent)

                Log.i("My Service","${Thread.currentThread().id}")
            }) {
                Text("Start Service")
            }
            ElevatedButton(onClick = {
                context.stopService(intent)
            }) {
                Text("Stop Service")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    ServiceTestTheme {
        HomePage()
    }
}