package com.debug.simplecheck

import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.InetSocketAddress
import javax.net.SocketFactory

private const val TAG = "Check Internet"

class NoInternetConnectionException(override val message: String) : Exception()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            cm.getNetworkCapabilities(cm.activeNetwork)
                ?: throw NoInternetConnectionException("No network connection")

            cm.activeNetwork?.let { network ->
                CoroutineScope(Dispatchers.IO).launch {
                    DoesNetworkHaveInternet.execute(network.socketFactory)
                }
            }

            Log.d(TAG, "With network connection")
        } catch (ex: NoInternetConnectionException) {
            Log.d(TAG, ex.message)
        }
    }
}

object DoesNetworkHaveInternet {

    // Execute this on a background
    fun execute(socketFactory: SocketFactory) {
        try {
            Log.d(TAG, "PINGING google.")
            val socket = socketFactory.createSocket() ?: throw IOException("Socket is null.")
            socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            socket.close()
            Log.d(TAG, "PING success.")
        } catch (ex: IOException) {
            Log.e(TAG, "No internet connection. $ex")
        }
    }
}