package com.example.movieassessment.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notesapproom.model.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Movie>)

    @Query("SELECT * FROM MOVIES WHERE title LIKE :query")
    suspend fun searchMovie(query: String?): List<Movie>

    @Query("SELECT * FROM MOVIES ORDER BY year DESC")
    fun getAllMovies(): LiveData<List<Movie>>
}