package com.evan.movieapp2025.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappnew.data.container.MovieServerContainer
import com.example.movieappnew.ui.model.DummyMovieData
import com.example.movieappnew.ui.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel: ViewModel() {

    private val _movie = MutableStateFlow<Movie?>(null)
    val movie: StateFlow<Movie?> = _movie

    fun getMovie(id: Int){
        viewModelScope.launch {
            _movie.value = MovieServerContainer().MovieServerRepository.GetMovieById(id)
        }
    }

    fun toggleIsLiked() {
        viewModelScope.launch {
            if(movie.value!!.isFavorite){
                MovieServerContainer().MovieServerRepository.UnlikeMovie(movie.value!!.id)
            } else {
                MovieServerContainer().MovieServerRepository.LikeMovie(movie.value!!.id)
            }
            getMovie(movie.value!!.id)
        }
    }

}