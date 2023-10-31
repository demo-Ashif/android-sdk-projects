package demo.dev.servicetest

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlin.random.Random

class MyService : Service() {
    private var mRandomNumber: Int = 0
    private var mIsRandomGeneratorOn: Boolean = false
    private val TAG = "MyService"

    private val min = 0
    private val max = 100


    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("My Service", "${Thread.currentThread().id}")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.i("My Service", "${Thread.currentThread().id} destroyed")
        super.onDestroy()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i(TAG,"In onUnbind")
        return super.onUnbind(intent)
    }

    private fun startRandomNumber() {
        while (mIsRandomGeneratorOn) {
            try {
                Thread.sleep(1000)
                if (mIsRandomGeneratorOn) {
                    mRandomNumber = Random(max).nextInt() + min
                    Log.i(TAG,"Thread Id: ${Thread.currentThread().id} - Random Number: $mRandomNumber")
                }

            } catch (e: Exception) {
                Log.i(TAG,"Thread Interrupted")
            }
        }
    }

    private fun stopRandomNumber() {
        mIsRandomGeneratorOn = false
    }

    fun getRandomNumber(): Int {
        return mRandomNumber
    }
}