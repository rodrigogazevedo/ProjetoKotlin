package com.example.projetomovies.view.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.example.projetomovies.R
import com.example.projetomovies.databinding.ActivityDetailsBinding
import com.example.projetomovies.models.repository.MovieRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val idMovie = intent.getIntExtra("idMovie", -1)

        MovieRepository.getMovie({ movie->
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
                .into(binding.posterMV)

            binding.posterMV.setOnClickListener {
                Glide.with(binding.root)
                    .load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
                    .transition(GenericTransitionOptions.with(R.anim.zoom_in))
                    .into(binding.posterEnlargeMV)

                posterIsEnlarger = true

                binding.title.visibility = View.GONE
                binding.genres.visibility = View.GONE
                binding.releaseDate.visibility = View.GONE
                binding.runtime.visibility = View.GONE
                binding.posterMV.visibility = View.GONE
                binding.ratingBar.visibility = View.GONE
                binding.voteCount.visibility = View.GONE
                binding.labelOverview.visibility = View.GONE
                binding.overview.visibility = View.GONE
                binding.btnFavorites.visibility = View.GONE
            }

            binding.posterEnlargeMV.setOnClickListener {
                Glide.with(binding.root)
                    .load("https://image.tmdb.org/t/p/w500/${movie.poster_path}")
                    .transition(GenericTransitionOptions.with(R.anim.zoom_out))
                    .into(binding.posterMV)
            }

        }, idMovie )

    }

}
