package com.example.movieassessment.service

import com.example.movieassessment.model.SearchResult
import com.example.movieassessment.utils.RetrofitInstance
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("/")
    suspend fun searchMovies(@Query("apikey") apiKey:String? = "6fc87060", @Query("s") search:String, @Query("type") type:String): Response<SearchResult>

    @GET("/")
    suspend fun getMovieDetails(@Query("apikey") apiKey:String? = "6fc87060",@Query("i") movieID:String): Response<SearchResult>
//    https://www.omdbapi.com/?apikey=6fc87060&s=Marvel&type=movie
}

object RetrofitInstance {
    val api: MovieService by lazy {
       RetrofitInstance.getRetrofitInstance().create(MovieService::class.java)
    }
}