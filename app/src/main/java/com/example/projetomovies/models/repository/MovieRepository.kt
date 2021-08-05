package com.example.projetomovies.models.repository

import android.content.Context
import androidx.room.Room
import com.example.projetomovies.MovieList
import com.example.projetomovies.TheMoviesApi
import com.example.projetomovies.models.model.AppDataBase
import com.example.projetomovies.models.model.MovieModel
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRepository {
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.themoviedb.org/3/movie/")
        .build()
    private val moviesApi: TheMoviesApi = retrofit.create(TheMoviesApi::class.java)
    var db: AppDataBase? = null

    fun initDataBase(context: Context) {
        if(db == null){
            db = Room.databaseBuilder(context, AppDataBase::class.java, "moviesFavorited")
                .build()
        }
    }

    fun AddFavorite(context: Context, movie: MovieModel) {
        initDataBase(context)
        CoroutineScope(GlobalScope.coroutineContext).launch {
            withContext(Dispatchers.IO) {
                db?.movieDao()?.insertFavorite(movie)
            }
        }
    }

    fun DeleteFavorite(context: Context, movie: MovieModel) {
        initDataBase(context)
        CoroutineScope(GlobalScope.coroutineContext).launch {
            withContext(Dispatchers.IO) {
                db?.movieDao()?.deleteFavorite(movie)
            }
        }
    }

    fun AllFavorite(context: Context, callback: (List<MovieModel>) -> Unit) {
        initDataBase(context)
        CoroutineScope(GlobalScope.coroutineContext).launch {
            withContext(Dispatchers.IO) {
                val listFavorite = db?.movieDao()?.getAllFavorite()
                withContext(Dispatchers.Main){
                    callback(listFavorite ?: listOf())
                }
            }
        }
    }

    fun getPopular(callback: (List<MovieModel>) -> Unit) {
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.IO) {
            withContext(Dispatchers.IO){
                val apiCalled = moviesApi.listPopular()
                apiCalled.enqueue(object : Callback<MovieList> {
                    override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                        if (response.body() != null){
                            callback(response.body()!!.results)
                        }else {
                            println("Lista Vazia")
                        }
                    }

                    override fun onFailure(call: Call<MovieList>, t: Throwable) {
                        //Toast.makeText(context,"Lista Caiu", Toast.LENGTH_LONG).show()
                        println("Lista Caiu")
                    }

                })
            }
        }
    }

    fun getNowPlaying(callback: (List<MovieModel>) -> Unit) {
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.IO) {
            withContext(Dispatchers.IO){
                val apiCalled = moviesApi.nowPlaying()
                apiCalled.enqueue(object : Callback<MovieList> {
                    override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                        if (response.body() != null){
                            callback(response.body()!!.results)
                        }else {
                            println("Lista Vazia")
                        }
                    }

                    override fun onFailure(call: Call<MovieList>, t: Throwable) {
                        //Toast.makeText(context,"Lista Caiu", Toast.LENGTH_LONG).show()
                        println("Lista Caiu")
                    }

                })
            }
        }
    }

    fun getUpcoming(callback: (List<MovieModel>) -> Unit) {
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.IO) {
            withContext(Dispatchers.IO){
                val apiCalled = moviesApi.upcoming()
                apiCalled.enqueue(object : Callback<MovieList> {
                    override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                        if (response.body() != null){
                            callback(response.body()!!.results)
                        }else {
                            println("Lista Vazia")
                        }
                    }

                    override fun onFailure(call: Call<MovieList>, t: Throwable) {
                        //Toast.makeText(context,"Lista Caiu", Toast.LENGTH_LONG).show()
                        println("Lista Caiu")
                    }

                })
            }
        }
    }

    fun getMovie(callback: (MovieModel) -> Unit, id: Int) {
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val callApi = moviesApi.getMovieById(id)

                callApi.enqueue(object : Callback<MovieModel> {
                    override fun onResponse(
                        call: Call<MovieModel>,
                        response: Response<MovieModel>
                    ) {
                        response.body()?.let{ movie ->
                            callback(movie)
                        }
                    }

                    override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                        println("Filme Não Veio")
                    }

                })
            }
        }
    }
}
