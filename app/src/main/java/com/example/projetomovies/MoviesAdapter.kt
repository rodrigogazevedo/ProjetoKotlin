package com.example.projetomovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projetomovies.databinding.MovieItemBinding

class MoviesViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root)

class MoviesAdapter(val clickMovie:()->Unit):
    RecyclerView.Adapter<MoviesViewHolder>() {

    val listMovies: MutableList<String> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: MoviesViewHolder, position: Int) {
        val item = listMovies.get(position)
        viewHolder.binding.titulo = item
        viewHolder.binding.itembackground.setOnClickListener {
            clickMovie()
        }
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    fun addItemList(list: List<String>) {
//        listMovies.clear()
        listMovies.addAll(list)
        notifyDataSetChanged()
    }


}