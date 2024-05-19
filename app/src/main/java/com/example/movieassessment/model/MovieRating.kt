package com.example.movieassessment.model

import com.google.gson.annotations.SerializedName

data class MovieRating(
    @SerializedName("Source")
    var Source:String? = null,

    @SerializedName("Value")
    var Value:String? = null
)
