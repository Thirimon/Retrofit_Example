package com.example.retrofit_example.Model

import com.example.retrofit_example.Network.RetrofitInstance

class MovieRepositry {
    private val movieService = RetrofitInstance.movieService

    suspend fun getMovies(): List<Movie> {
        return movieService.getMovies()
    }
}