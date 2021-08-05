package com.example.projetomovies.models.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieModel(
    @ColumnInfo(name = "title")
    val title:String,
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "poster_path")
    val poster_path: String?,
    @ColumnInfo(name = "overview")
    val overview: String?,
    @ColumnInfo(name = "release_date")
    val release_date: String,
    @ColumnInfo(name = "vote_count")
    val vote_count: Int,
    @ColumnInfo(name = "vote_average")
    val vote_average: Double,
    @ColumnInfo(name = "runtime")
    val runtime: Int?,
    @Ignore
    val genres: List<GenreModel> = listOf(),
//    @ColumnInfo(name = "isFavorite")
//    val isFavorite: Boolean = false
) {
    constructor(title: String, id: Int, poster_path: String?, overview: String?,
                release_date: String, vote_count: Int, vote_average: Double,
                runtime: Int?) :
            this(title, id, poster_path, overview, release_date, vote_count, vote_average, runtime,
                listOf())
}
