package com.example.retrofit_example.Network

import com.example.retrofit_example.Model.Movie
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface APIService{
 @GET("movielist.json")
suspend fun getMovies():List<Movie>
}