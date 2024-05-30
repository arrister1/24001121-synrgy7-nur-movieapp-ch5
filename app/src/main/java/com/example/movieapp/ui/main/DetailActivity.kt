package com.example.movieapp.ui.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.data.model.Movie
import com.example.movieapp.databinding.ActivityDetailBinding
import com.example.movieapp.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var _activityDetailBinding: ActivityDetailBinding
    private val binding get() = _activityDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ambil data dari intent
        val movie = intent.getParcelableExtra<Movie>("EXTRA_MOVIE")

        movie?.let {
            binding.tvDetailTitle.text = it.title
            binding.tvYearContent.text = it.date
            binding.tvDescContent.text = it.overview
            Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + it.poster)
                .into(binding.imgDetail)
        }

        binding.btnBack.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }

    }
}