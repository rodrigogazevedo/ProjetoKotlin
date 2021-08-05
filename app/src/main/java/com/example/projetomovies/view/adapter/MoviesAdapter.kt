package com.example.projetomovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projetomovies.databinding.MovieItemBinding
import com.example.projetomovies.models.model.MovieModel

class MoviesViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root)

//class MoviesAdapter(val clickMovie:(Int) -> Unit, val favoriteCallback: (Int, Boolean) -> Unit):
class MoviesAdapter(val clickMovie:(Int) -> Unit):
    RecyclerView.Adapter<MoviesViewHolder>() {

    private val listMovies: MutableList<MovieModel> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: MoviesViewHolder, position: Int) {
        val item = listMovies.get(index = position)
        viewHolder.binding.tituloTV.text = item.title
        Glide.with(viewHolder.binding.root)
            .load("https://image.tmdb.org/t/p/w500/${item.poster_path}")
            .into(viewHolder.binding.posterId)
        viewHolder.binding.itemBackground.setOnClickListener {
            clickMovie(item.id)
        }
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    fun addItemList(list: List<MovieModel>) {
        listMovies.clear()
        listMovies.addAll(list)
        notifyDataSetChanged()
    }


}