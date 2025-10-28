package com.example.movieappnew.data.repository

import com.example.movieappnew.data.service.MovieServerService
import com.example.movieappnew.ui.model.Movie

class MovieServerRepository(
    private val service: MovieServerService
) {
    suspend fun GetAllMovies(): List<Movie> {
        val dataFromAPI = service.GetMovies()
        if (dataFromAPI.code() == 200) {
            val dataMovie = mutableListOf<Movie>()
            val dataAPIBody = dataFromAPI.body()

            for (data in dataAPIBody!!) {
                val isLiked = service.CheckLike(data.id).body()!!.liked
                val movie = Movie(
                    id = data.id,
                    title = data.title,
                    description = data.description,
                    rating = data.rating,
                    genre = data.genre,
                    director = data.director,
                    releaseYear = data.releaseYear,
                    posterPath = data.posterPath,
                    posterResId = 0,
                    isFavorite = isLiked
                )
                dataMovie.add(movie)
            }
            return dataMovie
        } else {
            return emptyList()
        }
    }
}