package com.debug.debugflix

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.debug.debugflix.network.DiscoverAPI
import com.debug.debugflix.network.ServiceProvider
import com.debug.debugflix.network.TmdbInterceptor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import retrofit2.HttpException

class MainFragment : Fragment() {

    private lateinit var movies: RecyclerView
    private val tmdbInterceptor = TmdbInterceptor()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movies = view.findViewById(R.id.movies)

        getMovies()
    }

    private fun getMovies() {
        lifecycleScope.launch(Dispatchers.IO) {

            try {
                val result = ServiceProvider(tmdbInterceptor).createService(DiscoverAPI::class.java)
                    .getMovies().results

                withContext(Dispatchers.Main) {
                    movies.adapter = MoviesAdapter(result)
                }
            } catch (httpException: HttpException){
                // handle httpException
            } catch (exception: Exception){
                //handle others exception
            }
        }
    }
}

/*
* The following function can be used to parse the error body from a request and return a class
* */
inline fun <reified ClassType> HttpException.getErrorResponse(): ClassType? {
    return this.response()?.errorBody()?.let { body ->
        Json.decodeFromString(body.string())
    }
}


















