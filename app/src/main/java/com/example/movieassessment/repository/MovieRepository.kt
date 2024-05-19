package com.example.movieassessment.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieassessment.database.MovieDao
import com.example.movieassessment.model.MovieEntity
import com.example.movieassessment.service.MovieService
import com.example.notesapproom.model.Movie
import com.example.notesapproom.model.toEntity
import com.example.notesapproom.model.toModel

class MovieRepository(private val movieDao: MovieDao, private val apiService: MovieService)  {

    private val _movies = MutableLiveData<List<MovieEntity>>()
    val movies: LiveData<List<MovieEntity>> = _movies

    suspend fun searchMovie(query: String?): List<MovieEntity> {
        val response = apiService.searchMovies(search = query.toString(), type = "movie")
        Log.v("API DATA", response.toString())
        if (response.isSuccessful) {
            val movies = response.body()?.Search ?: emptyList()
            // Save to local database
            movieDao.insertAll(movies.map { it.toModel() })
            return movies.toList()
        } else {
            // If API call fails, fetch from local database
            return movieDao.searchMovie(query).map { it.toEntity() }
        }
    }
    fun getAllMovie() = movieDao.getAllMovies();
}