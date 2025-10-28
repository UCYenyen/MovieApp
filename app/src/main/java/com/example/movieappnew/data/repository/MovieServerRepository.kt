package com.example.movieappnew.data.repository

import com.example.movieappnew.data.dto.RequestLogin
import com.example.movieappnew.data.dto.RequestRegister
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
    suspend fun GetMovieById(id: Int): Movie {
        val movieItem = service.GetMovieById(id).body()!!
        val isLiked = service.CheckLike(movieItem.id).body()!!.liked
        val movie = Movie(
            id = movieItem.id,
            title = movieItem.title,
            description = movieItem.description,
            rating = movieItem.rating,
            genre = movieItem.genre,
            director = movieItem.director,
            releaseYear = movieItem.releaseYear,
            posterPath = movieItem.posterPath,
            isFavorite = isLiked,
            posterResId = 0
        )
        return movie
    }

    suspend fun LikeMovie(id: Int): Boolean {
        return try {
            val res = service.LikeMovie(id)
            res.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    suspend fun UnlikeMovie(id: Int): Boolean {
        return try {
            val res = service.DislikeMovie(id)
            res.isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    suspend fun Login(email: String, password: String): String {
        val data = RequestLogin(email, password)
        val serverToken = service.Login(data).body()!!
        return serverToken.token
    }

    suspend fun Register(name: String, email: String, password: String): String {
        val data = RequestRegister(email, name, password)
        val serverToken = service.Register(data).body()!!
        return serverToken.token
    }
}