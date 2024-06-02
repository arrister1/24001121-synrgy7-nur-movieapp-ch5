package com.example.movieapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.data.model.Movie
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.helper.DataStore
import com.example.movieapp.ui.adapter.MovieAdapter
import com.example.movieapp.ui.user.ProfileActivity
import com.example.movieapp.ui.user.UserActivity
import com.example.movieapp.ui.viewmodel.MovieViewModel
import com.example.movieapp.ui.viewmodel.MovieViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), MovieAdapter.OnItemClickListener {
    private lateinit var _activityMainBinding: ActivityMainBinding
    private val binding get() = _activityMainBinding

    private lateinit var dataStore: DataStore


    private val viewModel: MovieViewModel by viewModels {
        MovieViewModelFactory.getInstance(this)
    }
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnProfile.setOnClickListener {
            startActivity(Intent(this@MainActivity,ProfileActivity::class.java ))
            finish()
        }

        dataStore = DataStore(this)

        setupUsername()
        setupRecyclerView()
        setupObservers()

        // Panggil ViewModel untuk mengambil data film
        viewModel.getMoviePopular()
    }

    private fun setupUI() {
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupUsername() {
        lifecycleScope.launch {
            dataStore.username.collect{username ->
                binding.tvUname.text = username ?: "Guest"
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = MovieAdapter(this)
        binding.homeRv.layoutManager = LinearLayoutManager(this)
        binding.homeRv.setHasFixedSize(true)
        binding.homeRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.movieResponse.observe(this, Observer { movieResponse ->
            movieResponse?.let {
                // Perbarui adapter dengan data film
                adapter.setMovieList(it.movies)
            } ?: run {
                // Tangani kasus kegagalan, misalnya dengan menampilkan pesan kesalahan
                Log.e("MainActivity", "Failed to fetch movies")
            }
        })
    }

    override fun onItemClick(movie: Movie) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("EXTRA_MOVIE", movie)
        startActivity(intent)
        //Toast.makeText(this, "Clicked on: ${movie.title}", Toast.LENGTH_SHORT).show()
    }
}
