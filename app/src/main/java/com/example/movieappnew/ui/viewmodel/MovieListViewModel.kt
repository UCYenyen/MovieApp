package com.example.movieappnew.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movieappnew.ui.model.DummyMovieData
import com.example.movieappnew.ui.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MovieListViewModel: ViewModel() {
    // Set
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    // Get
    val movies: StateFlow<List<Movie>> = _movies

    init{
        loadMovie()
    }
    fun loadMovie(){
        _movies.value = DummyMovieData.movies.toList()
    }

    fun toggleIsLiked(movie: Movie){
        val index = DummyMovieData.movies.indexOfFirst {
            it.title == movie.title
        }

        if(index == -1) return

        val updated = DummyMovieData.movies[index].copy(
            isFavorite = !DummyMovieData.movies[index].isFavorite
        )

        DummyMovieData.movies[index] = updated
        loadMovie()
    }
}