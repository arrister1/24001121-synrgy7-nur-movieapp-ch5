package com.example.movieapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.api.ApiClient
import com.example.movieapp.data.model.MovieResponse
import com.example.movieapp.helper.DataStore
import com.example.movieapp.helper.Resource
import com.example.movieapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel(private val repository: MovieRepository, private var pref: DataStore) : ViewModel() {

    private var _movieResponse = MutableLiveData<MovieResponse?>()
    val movieResponse: LiveData<MovieResponse?> get() = _movieResponse

//
//    fun getMoviePopular() = liveData(Dispatchers.IO) {
//        emit(repository.moviePopular())
//    }

    fun getMoviePopular() {
        ApiClient.instance.getMoviePopular().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val data = response.body()
                _movieResponse.postValue(data)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _movieResponse.postValue(null)
            }

        })
    }

//    fun setIncrement(){
//        viewModelScope.launch {
//            pref.incrementCounter()
//        }
//    }
//
//    fun getIncrement() = pref.getCounter().asLiveData()
//
//    fun setLogin(isLogin: Boolean){
//        viewModelScope.launch {
//            pref.setLogin(isLogin)
//        }
//    }
//
//    fun getLogin() = pref.getLogin().asLiveData()
}

