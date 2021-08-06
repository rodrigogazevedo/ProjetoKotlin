package com.example.projetomovies.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projetomovies.MovieList
import com.example.projetomovies.models.model.MovieModel
import com.example.projetomovies.models.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {
    private val liveDataList: MutableLiveData<List<MovieModel>?> = MutableLiveData(null)
    private val movieLiveData: MutableLiveData<List<MovieModel>> = MutableLiveData(null)

    fun getMovieListOberserver(): MutableLiveData<List<MovieModel>> {
        return movieLiveData
    }

    fun movieListApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            //val response = MovieRepository.apiMovie.getPopularService(API_KEY)
            //val responseLatest = MovieRepository.apiMovie.getPopularService(API_KEY)
        }
    }

}
