package demo.dev.servicetest

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("My Service","${Thread.currentThread().id}")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.i("My Service","${Thread.currentThread().id} destroyed")
        super.onDestroy()
    }
}