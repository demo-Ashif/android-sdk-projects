package demo.dev.servicetest

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kotlin.concurrent.thread
import kotlin.random.Random

class MyService : Service() {
    private var mRandomNumber: Int = 0
    private var mIsRandomGeneratorOn: Boolean = false
    private val TAG = "MyService"

    private val binder = LocalBinder()

    private val min = 0
    private val max = 100

    inner class LocalBinder : Binder() {
        // Return this instance of LocalService so clients can call public methods.
        fun getService(): MyService = this@MyService
    }


    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "In onStartCommand: ${Thread.currentThread().id}")

        val thread = Thread {
            startRandomNumberGenerator()
        }

        thread.start()

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        stopRandomNumberGenerator()
        Log.i(TAG, "Service Destroyed")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i(TAG, "In onUnbind")
        return super.onUnbind(intent)
    }

    private fun startRandomNumberGenerator() {
        while (mIsRandomGeneratorOn) {
            try {
                Thread.sleep(1000)
                if (mIsRandomGeneratorOn) {
                    mRandomNumber = Random.nextInt(max,min)
                    Log.i(
                        TAG,
                        "Thread Id: ${Thread.currentThread().id} - Random Number: $mRandomNumber"
                    )
                }

            } catch (e: Exception) {
                Log.i(TAG, "Thread Interrupted")
            }
        }
    }

    private fun stopRandomNumberGenerator() {
        mIsRandomGeneratorOn = false
    }

    fun getRandomNumber(): Int {
        return mRandomNumber
    }
}