package com.example.movieapp.repository

import com.example.movieapp.helper.RemoteDataSource

class MovieRepository(private val remoteDataSource: RemoteDataSource) {
    suspend fun moviePopular() = remoteDataSource.moviePopular()
}