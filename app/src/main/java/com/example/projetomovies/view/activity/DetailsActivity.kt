package com.example.projetomovies.view.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.projetomovies.R
import com.example.projetomovies.databinding.ActivityDetailsBinding
import com.example.projetomovies.models.model.MovieModel
import com.example.projetomovies.models.repository.MovieRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private var isBig: Boolean = false

    companion object {
        const val ID_MOVIE: String = "id_movie"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val idMovie = intent.getIntExtra(ID_MOVIE, -1)

        MovieRepository.getMovie({ movie ->
            binding.title.text = movie.title
            binding.overview.text = movie.overview
            binding.runtime.text = "${movie.runtime} min"

            val date = LocalDate.parse(movie.release_date)
            val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy")
            val formattedDate = date.format(formatter)
            binding.releaseDate.text = formattedDate

            binding.voteCount.text = "${movie.vote_count} votes"

            if (movie.genres.isNotEmpty()) {
                val genres = movie.genres.joinToString(
                    separator = " | ",
                    transform = { genre -> genre.name }
                )

                binding.genres.text = genres
            } else {
                binding.genres.visibility = View.GONE
            }

            movie.vote_average.let { rate ->
                binding.ratingBar.rating = (rate / 2).toFloat()
            }

            var posterIsEnlarger = false

            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(binding.posterMV)


            binding.posterMV.setOnClickListener {
                Glide.with(binding.root)
                    .load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .transition(GenericTransitionOptions.with(R.anim.zoom_in))
                    .into(binding.posterEnlargeMV)

                binding.title.visibility = View.GONE
                binding.genres.visibility = View.GONE
                binding.releaseDate.visibility = View.GONE
                binding.runtime.visibility = View.GONE
                binding.posterMV.visibility = View.GONE
                binding.ratingBar.visibility = View.GONE
                binding.voteCount.visibility = View.GONE
                binding.labelOverview.visibility = View.GONE
                binding.overview.visibility = View.GONE
            }

            binding.posterEnlargeMV.setOnClickListener {

                binding.posterMV.visibility = View.GONE
                Glide.with(binding.root)
                    .load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .transition(GenericTransitionOptions.with(R.anim.zoom_out))
                    .into(binding.posterEnlargeMV)

                binding.title.visibility = View.VISIBLE
                binding.genres.visibility = View.VISIBLE
                binding.releaseDate.visibility = View.VISIBLE
                binding.runtime.visibility = View.VISIBLE
                binding.posterMV.visibility = View.VISIBLE
                binding.ratingBar.visibility = View.VISIBLE
                binding.voteCount.visibility = View.VISIBLE
                binding.labelOverview.visibility = View.VISIBLE
                binding.overview.visibility = View.VISIBLE
            }

        }, idMovie)

    }

}
