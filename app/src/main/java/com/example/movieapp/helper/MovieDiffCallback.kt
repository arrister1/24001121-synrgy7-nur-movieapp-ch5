package com.example.movieapp.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.model.MovieResponse


//buat ngecek apa ada perbuahan list note. Akan dipanggil di adapter
class MovieDiffCallback(private val oldNoteList: List<Movie>, private val newNoteList: List<Movie>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldNoteList.size

    override fun getNewListSize(): Int = newNoteList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNoteList[oldItemPosition].id == newNoteList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldNoteItem = oldNoteList[oldItemPosition]
        val newNoteItem = newNoteList[newItemPosition]
        return oldNoteItem.title == newNoteItem.title && oldNoteItem.overview == newNoteItem.overview
    }

}