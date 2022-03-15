package com.debug.debugflix.network

import com.debug.debugflix.data.model.BaseResponse
import com.debug.debugflix.data.model.MovieRemote
import retrofit2.http.GET

interface DiscoverAPI {

    @GET("discover/movie")
    suspend fun getMovies(): BaseResponse<List<MovieRemote>>
}