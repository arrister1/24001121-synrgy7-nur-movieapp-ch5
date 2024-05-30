package com.example.movieapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id")
    val id : String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("release_date")
    val date: String?,

    @SerializedName("poster_path")
    val poster: String?,

    @SerializedName("overview")
    val overview: String?,
): Parcelable
//{
//    constructor(): this("", "", "", "", "")}