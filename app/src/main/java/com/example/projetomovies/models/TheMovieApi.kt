package com.example.projetomovies

import retrofit2.Call
import retrofit2.http.GET
import com.example.projetomovies.models.constrants.ApiConst.API_KEY
import com.example.projetomovies.models.constrants.ApiConst.LANGUAGE
import com.example.projetomovies.models.model.MovieModel
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMoviesApi {
    @GET("popular")
    fun listPopular(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1,
    ): Call<MovieList>

    @GET("now_playing")
    fun nowPlaying(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1,
    ): Call<MovieList>

    @GET("upcoming")
    fun upcoming(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = 1,
    ): Call<MovieList>

    @GET("{idMovieURL}")
    fun getMovieById(
        @Path("idMovieURL") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE
    ): Call<MovieModel>

}

class MovieList(val page: Int, val results: List<MovieModel>)