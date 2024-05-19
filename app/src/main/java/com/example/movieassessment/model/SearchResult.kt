package com.example.movieassessment.model

import com.example.notesapproom.model.Movie
import com.google.gson.annotations.SerializedName


data class SearchResult (

    @SerializedName("Search")
    var Search:ArrayList<MovieEntity> = arrayListOf(),

    @SerializedName("totalResults")
    var totalResults : String? = null,

    @SerializedName("Response")
    var Response: String? = null
)