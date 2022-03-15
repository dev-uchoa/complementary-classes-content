package com.debug.lifecycle

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class FirstFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    companion object {
        fun newInstance() = FirstFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "FirstFragment onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.initViewModel()
        Log.d(TAG, "FirstFragment viewModel $viewModel")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "FirstFragment onCreateView")
        return inflater.inflate(R.layout.first_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "FirstFragment onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "FirstFragment onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "FirstFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "FirstFragment onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "FirstFragment onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "FirstFragment onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "FirstFragment onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "FirstFragment onDetach")
    }
}