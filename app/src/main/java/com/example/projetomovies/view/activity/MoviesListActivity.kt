package com.example.projetomovies.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetomovies.MovieList
import com.example.projetomovies.MoviesAdapter
import com.example.projetomovies.databinding.ActivityMinhaListaFilmeBinding
import com.example.projetomovies.models.repository.MovieRepository

class MoviesListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMinhaListaFilmeBinding
    //val viewModel: MovieListViewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)

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
            callPopular()
        }
    }

    private fun callPopular() {
        val adapter = callMovieAdapter()

        binding.movieRV.adapter = adapter

        MovieRepository.getPopular { list ->
            adapter.notifyDataSetChanged()
            adapter.addItemList(list)
        }
    }

    private fun callNowPlaying() {
        val adapter = callMovieAdapter()

        binding.movieRV.adapter = adapter

        MovieRepository.getNowPlaying { list ->
            adapter.notifyDataSetChanged()
            adapter.addItemList(list)
        }
    }

    private fun callUpcoming() {
        val adapter = callMovieAdapter()

        binding.movieRV.adapter = adapter

        MovieRepository.getUpcoming { list ->
            adapter.notifyDataSetChanged()
            adapter.addItemList(list)
        }
    }

    private fun callFavorite() {
        TODO("Not yet implemented")
    }

    private fun callMovieAdapter(): MoviesAdapter {
        val adapter = MoviesAdapter { id ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("idMovie", id)
            startActivity(intent)
        }

        return adapter
    }

}