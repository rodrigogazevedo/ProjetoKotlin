package com.example.projetomovies.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projetomovies.MoviesAdapter
import com.example.projetomovies.databinding.ActivityMinhaListaFilmeBinding
import com.example.projetomovies.models.model.MovieModel
import com.example.projetomovies.models.repository.MovieRepository
import com.example.projetomovies.view.activity.DetailsActivity.Companion.ID_MOVIE

class MoviesListActivity : AppCompatActivity() {

    companion object {
        const val ID_LIST: String = "id_list"
    }

    private lateinit var binding: ActivityMinhaListaFilmeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayout()
        setupList()

    }

    private fun initLayout() {
        binding = ActivityMinhaListaFilmeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun setupList() {

        binding.btnNowPlayingMovies.setOnClickListener {
            callNowPlaying()
        }

        binding.btnPopularMovies.setOnClickListener {
            callPopular()
        }

        binding.btnUpcomingMovies.setOnClickListener {
            callUpcoming()
        }

        binding.btnFavoriteMovies.setOnClickListener {
            callFavorite()
        }
    }

    private fun callPopular() {
        val adapter = callMovieAdapter()

        binding.movieRV.adapter = adapter

        MovieRepository.getPopular { list ->
            callUpdateListFavorite(list)
            adapter.notifyDataSetChanged()
            adapter.addItemList(list)
        }
    }

    private fun callNowPlaying() {
        val adapter = callMovieAdapter()

        binding.movieRV.adapter = adapter

        MovieRepository.getNowPlaying { list ->
            callUpdateListFavorite(list)
            adapter.notifyDataSetChanged()
            adapter.addItemList(list)
        }
    }

    private fun callUpcoming() {

        val adapter = callMovieAdapter()

        binding.movieRV.adapter = adapter

        MovieRepository.getUpcoming { list ->
            callUpdateListFavorite(list)
            adapter.notifyDataSetChanged()
            adapter.addItemList(list)
        }
    }

    private fun callFavorite() {
        val adapter = callMovieAdapter()

        binding.movieRV.adapter = adapter

        MovieRepository.allFavorite(this) { list ->
            list.forEach { movie ->
                movie.isFavorite = true
            }
            adapter.notifyDataSetChanged()
            adapter.addItemList(list)
        }
    }


    private fun callUpdateListFavorite(list: List<MovieModel>) {
        val adapter = callMovieAdapter()

        binding.movieRV.adapter = adapter

        MovieRepository.allFavorite(this) {
            list.forEach { movie ->
                movie.isFavorite = it.any { movieModel ->
                    movie.id == movieModel.id
                }
            }
            adapter.notifyDataSetChanged()
            adapter.addItemList(list)
        }
    }


    private fun callMovieAdapter(): MoviesAdapter {
        val idList = intent.getIntExtra(ID_LIST, -1)

        val adapter = MoviesAdapter ({ id ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(ID_MOVIE, id)
            intent.putExtra(ID_LIST, idList)
            startActivity(intent)
        }, { movie, isFavorite ->
            if(isFavorite) MovieRepository.addFavorite(this,movie)
            else MovieRepository.deleteFavorite(this, movie)

        })

        adapter.notifyDataSetChanged()

        return adapter
    }

}