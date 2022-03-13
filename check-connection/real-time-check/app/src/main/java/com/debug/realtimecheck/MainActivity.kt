package com.debug.realtimecheck

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var connectionLiveData: ConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectionLiveData = ConnectionLiveData(this)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.networkText)

        connectionLiveData.observe(this) { isNetworkAvailable ->
            textView.text = if (isNetworkAvailable) {
                "With internet"
            } else {
                "Without internet"
            }
        }
    }
}