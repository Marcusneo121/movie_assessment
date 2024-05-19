package com.example.movieassessment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.example.movieassessment.database.MovieDatabase
import com.example.movieassessment.model.MovieEntity
import com.example.movieassessment.model.SearchResult
import com.example.movieassessment.repository.MovieRepository
import com.example.movieassessment.service.RetrofitInstance
import com.example.notesapproom.model.Movie
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieViewModel(app: Application): AndroidViewModel(app)  {

    private val movieRepository: MovieRepository
    init {
        val movieDao = MovieDatabase.getDatabase(app).getMovieDao()
        movieRepository = MovieRepository(movieDao, RetrofitInstance.api)
    }

    fun searchMovies(query: String) {
        viewModelScope.launch {
            movieRepository.searchMovie(query)
        }
    }

    fun getAllMovies() = movieRepository.getAllMovie();
}