package com.example.retrofit_example.ViewModel

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit_example.MainActivity
import com.example.retrofit_example.Model.Movie
import com.example.retrofit_example.Model.MovieRepositry
import com.example.retrofit_example.Network.APIService

import kotlinx.coroutines.launch


class MovieViewModel: ViewModel() {
    private val repository = MovieRepositry()

    private val _movies = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
        get() {
            return _movies
        }

    fun fetchMovies() {
        viewModelScope.launch {
            try {
                val cards = repository.getMovies()
                _movies.value = cards
                Log.e("FetchCreditCard", _movies.value.toString());
            } catch (e: Exception) {
                // Handle error
                Log.e("FetchCreditCard", e.message.toString());
            }
        }
    }
}