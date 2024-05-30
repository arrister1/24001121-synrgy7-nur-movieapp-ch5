package com.example.movieapp.helper

import com.example.movieapp.data.api.ApiService

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun moviePopular() = apiService.getMoviePopular()
}