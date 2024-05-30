package com.example.movieapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.ViewSwitcher.ViewFactory
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.data.api.ApiClient
import com.example.movieapp.data.api.ApiService
import com.example.movieapp.data.model.MovieResponse
import com.example.movieapp.data.model.Movie
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.helper.DataStore
import com.example.movieapp.helper.RemoteDataSource
import com.example.movieapp.repository.MovieRepository
import com.example.movieapp.ui.adapter.MovieAdapter
import com.example.movieapp.ui.viewmodel.MovieViewModel
import com.example.movieapp.ui.viewmodel.MovieViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
//
//class MainActivity : AppCompatActivity() {
//    private lateinit var _activityMainBinding: ActivityMainBinding
//    private val binding get() = _activityMainBinding
//
//    private val viewModel: MovieViewModel by viewModels {
//        MovieViewModelFactory.getInstance(this)
//    }
//    private lateinit var adapter: MovieAdapter
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        binding.homeRv.layoutManager = LinearLayoutManager(this)
//        binding.homeRv.setHasFixedSize(true)

class MainActivity : AppCompatActivity(), MovieAdapter.OnItemClickListener {
    private lateinit var _activityMainBinding: ActivityMainBinding
    private val binding get() = _activityMainBinding

    private val viewModel: MovieViewModel by viewModels {
        MovieViewModelFactory.getInstance(this)
    }
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupObservers()

        // Panggil ViewModel untuk mengambil data film
        viewModel.getMoviePopular()
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
    // Tangani klik item di sini
        //Toast.makeText(this, "Clicked on: ${movie.title}", Toast.LENGTH_SHORT).show()
        // Anda juga dapat memulai aktivitas baru atau melakukan tindakan lainnya
    }
}


//class MainActivity : AppCompatActivity() {
//    private lateinit var _activityMainBinding: ActivityMainBinding
//    private val binding get() = _activityMainBinding
//
//    private val viewModel: MovieViewModel by viewModels {
//        MovieViewModelFactory.getInstance(this)
//    }
//    private lateinit var adapter: MovieAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        setupRecyclerView()
//        setupObservers()
//
//        // Panggil ViewModel untuk mengambil data film
//        viewModel.getMoviePopular()
//    }
//
//    private fun setupRecyclerView() {
//        adapter = MovieAdapter()
//        binding.homeRv.layoutManager = LinearLayoutManager(this)
//        binding.homeRv.setHasFixedSize(true)
//        binding.homeRv.adapter = adapter
//    }
//
//    private fun setupObservers() {
//        viewModel.movieResponse.observe(this, Observer { movieResponse ->
//            movieResponse?.let {
//                // Perbarui adapter dengan data film
//                adapter.setMovieList(it.movies)
//            } ?: run {
//                // Tangani kasus kegagalan, misalnya dengan menampilkan pesan kesalahan
//                Log.e("MainActivity", "Failed to fetch movies")
//            }
//        })
//    }
//}



//        viewModel.getMoviePopular().observe(this, Observer{ movieResponse ->
//            movieResponse?.let{
//                Log.e
//            }
//        }){








//    private fun setupViewModel() {
//        val apiService = ApiClient.instance
//        val remoteDataSource = RemoteDataSource(apiService)
//        val dataStore = DataStore(this)
//
//        val movieRepository = MovieRepository(remoteDataSource)
//
//        val viewModelProviderFactory = MovieViewModelFactory(remoteDataSource, dataStore)
//        movieViewModel = ViewModelProvider(this, viewModelProviderFactory)[MovieViewModel::class.java]
//    }


//
//
//private lateinit var binding: ActivityMainBinding
//
//override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    binding = ActivityMainBinding.inflate(layoutInflater)
//    setContentView(binding.root)
//
//
//    binding.homeRv.layoutManager = LinearLayoutManager(this)
//    binding.homeRv.setHasFixedSize(true)
//    getMovieList { movies: List<Movie> ->
//        binding.homeRv.adapter = MovieAdapter(movies)
//    }
//}
//
//private fun getMovieList(callback: (List<Movie>) -> Unit){
//    ApiClient.instance.getMoviePopular().enqueue(object: Callback<MovieResponse>{
//        override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
//            return callback(response.body()!!.movies)
//        }
//
//        override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
//            TODO("Not yet implemented")
//        }
//
//    })
//}