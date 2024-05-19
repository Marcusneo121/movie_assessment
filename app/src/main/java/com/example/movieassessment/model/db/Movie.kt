package com.example.notesapproom.model
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieassessment.model.MovieEntity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movies")
@Parcelize
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title:String? = null,
    val year: String? = null,
    val imdbID: String? = null,
    val type: String? = null,
    val poster: String? = null
): Parcelable

fun Movie.toEntity(): MovieEntity {
    return MovieEntity(
        title = this.title,
        year = this.year,
        poster = this.poster,
        imdbID = this.imdbID,
        type = this.type,
    )
}

fun MovieEntity.toModel(): Movie {
    return Movie(
        title = this.title,
        year = this.year,
        poster = this.poster,
        imdbID = this.imdbID,
        type = this.type,
    )
}