package com.example.movieapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.data.model.Movie
import com.example.movieapp.databinding.ItemListBinding
import com.example.movieapp.helper.MovieDiffCallback
import com.example.movieapp.ui.viewmodel.MovieViewModel



class MovieAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val movieList = ArrayList<Movie>()

    interface OnItemClickListener {
        fun onItemClick(movie: Movie)
    }

    fun setMovieList(movieList: List<Movie>) {
        val diffCallback = MovieDiffCallback(this.movieList, movieList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.movieList.clear()
        this.movieList.addAll(movieList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    inner class ViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(movieList[position])
                }
            }
        }

        fun bind(movie: Movie) {
            binding.tvMovieTitle.text = movie.title
            binding.tvMovieYear.text = movie.date
            binding.tvMovieDesc.text = movie.overview
            Glide.with(binding.root.context).load(IMAGE_BASE + movie.poster).into(binding.listImg)
        }
    }
}
