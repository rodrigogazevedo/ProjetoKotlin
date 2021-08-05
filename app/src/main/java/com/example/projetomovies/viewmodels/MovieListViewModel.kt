package com.example.projetomovies.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projetomovies.MovieList
import com.example.projetomovies.models.model.MovieModel
import com.example.projetomovies.models.repository.MovieRepository

class MovieListViewModel : ViewModel() {
    private val liveDataList: MutableLiveData<List<MovieModel>?> = MutableLiveData(null)
    private val liveDataId: MutableLiveData<Int?> = MutableLiveData(null)

    fun getPopular() {
//        MovieRepository.getPopular {
//            liveDataList.postValue(it)
//        }
    }

    fun getLiveDataList() : MutableLiveData<List<MovieModel>?> {
        return liveDataList
    }

    fun getLiveDataId():LiveData<Int?> = liveDataId
}

//private fun <T> MutableLiveData<T>.postValue(it: List<MovieModel>) {
//
//}
