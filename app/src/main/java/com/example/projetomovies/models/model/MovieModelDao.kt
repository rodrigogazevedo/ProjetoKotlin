package com.example.projetomovies.models.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieModelDao {
    @Insert
    fun insertFavorite(movie: MovieModel)

    @Delete
    fun deleteFavorite(movie: MovieModel)

    @Query("SELECT * FROM movie")
    fun getAllFavorite(): List<MovieModel>
}