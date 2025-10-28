package com.example.movieappnew.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.movieappnew.ui.model.DummyMovieData
import com.example.movieappnew.ui.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AddMovieViewModel : ViewModel() {

    private val _movie = MutableStateFlow(
        Movie(
            id = -1,
            title = "",
            description = "",
            rating = 3.0,
            genre = "",
            director = "",
            releaseYear = 0,
            posterPath = "",
            isFavorite = false,
            posterResId = -1
        )
    )
    val movie: StateFlow<Movie> = _movie

    fun onTitleChange(v: String)       { _movie.update { it.copy(title = v) } }
    fun onDescriptionChange(v: String) { _movie.update { it.copy(description = v) } }
    fun onGenreChange(v: String)       { _movie.update { it.copy(genre = v) } }
    fun onDirectorChange(v: String)    { _movie.update { it.copy(director = v) } }
    fun onRatingChange(v: Int)         { _movie.update { it.copy(rating = v.toDouble()) } }
    fun onReleaseYearChange(v: String) {
        val year = v.toIntOrNull() ?: 0
        _movie.update { it.copy(releaseYear = year) }
    }
    fun onImageSelected(uri: Uri?)     {
        _movie.update { it.copy(posterPath = uri?.toString().orEmpty()) }
    }

    fun onSubmit() {
        val current = _movie.value
        val newMovie = current.copy()
        _movie.value = Movie(
            id = -1,
            title = "",
            description = "",
            rating = 3.0,
            genre = "",
            director = "",
            releaseYear = 0,
            isFavorite = false,
            posterResId = -1
        )
        DummyMovieData.movies.add(newMovie)
    }

}