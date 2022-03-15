package com.debug.lifecycle

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    fun initViewModel(){
        // function to initiate the ViewModel on the fragments
    }

    override fun onCleared() {
        Log.d(TAG, "ViewModel onCleared")
        super.onCleared()
    }
}