package com.debug.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

const val TAG = "Lifecycle"

class MainActivity : AppCompatActivity() {

//    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Activity onCreate")

        setContentView(R.layout.main_activity)
//        viewModel.initViewModel()
//        Log.d(TAG, "Activity viewModel $viewModel")

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, FirstFragment.newInstance())
                .commitNow()
        }

        val btn = findViewById<Button>(R.id.btn_change)
        btn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SecondFragment.newInstance())
                .commitNow()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "Activity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Activity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Activity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Activity onStop")
    }

    override fun onDestroy() {
        Log.d(TAG, "Activity onDestroy")
        super.onDestroy()
    }
}