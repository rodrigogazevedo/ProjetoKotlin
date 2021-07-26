package com.example.projetomovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projetomovies.databinding.ActivityMainBinding

class DetailsActivity: AppCompatActivity() {

    companion object{
        const val EXTRA_ID = "com.example.projetomovies.DetailsActivity.EXTRA_ID"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.titulo="Annabelle"
    }
}