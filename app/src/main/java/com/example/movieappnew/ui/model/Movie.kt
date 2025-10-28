package com.example.movieappnew.ui.model
data class Movie(
    val id: Int,
    val title: String,
    val description: String,
    val rating: Double,
    val genre: String,
    val director: String,
    val releaseYear: Int,
    val posterResId: Int,
    val posterPath: String = "",
    val isFavorite: Boolean = false
)
