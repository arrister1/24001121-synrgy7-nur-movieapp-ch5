package com.example.movieapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.data.api.ApiClient
import com.example.movieapp.helper.DataStore
import com.example.movieapp.helper.RemoteDataSource
import com.example.movieapp.repository.MovieRepository

class MovieViewModelFactory(val remoteDataSource: RemoteDataSource, val pref: DataStore) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: MovieViewModelFactory? = null

        fun getInstance(context: Context): MovieViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: MovieViewModelFactory(
                    RemoteDataSource(ApiClient.instance),
                    DataStore(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(MovieRepository(remoteDataSource), pref) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}