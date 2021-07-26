package com.example.projetomovies

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projetomovies.databinding.ActivityMinhaListaFilmeBinding

class MoviesList : AppCompatActivity() {

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
        val adapter = MoviesAdapter {
            val details = Intent(this, MovieListDetails::class.java)
            startActivity(details)
        }
        binding.movieRV.adapter = adapter
        val list: List<String> = List(10) { "Annabelle ${it+1}" }
        adapter.addItemList(list)
    }


    private fun openDetailsActivity() {
        val intent = Intent(this, MovieListDetails::class.java)
        startActivity(intent)
    }

}