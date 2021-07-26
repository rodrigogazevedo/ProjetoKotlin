package com.example.projetomovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projetomovies.databinding.ActivityMainBinding

class MovieListDetails: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}