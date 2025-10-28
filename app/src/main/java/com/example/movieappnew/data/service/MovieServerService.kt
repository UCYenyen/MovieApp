package com.example.movieappnew.data.service

import com.example.movieappnew.data.dto.RequestLogin
import com.example.movieappnew.data.dto.RequestRegister
import com.example.movieappnew.data.dto.ResponseMovies
import com.example.movieappnew.data.dto.ResponseStatusLike
import com.example.movieappnew.data.dto.ResponseToken
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MovieServerService {
    @GET("api/movies")
    suspend fun GetMovies(): Response<ResponseMovies>

    @GET("api/movies/{id}")
    suspend fun GetMovieById(@Path("id") id: Int): Response<ResponseMovies>

    @GET("api/movies/{id}/like")
    suspend fun CheckLike(@Path("id") id: Int): Response<ResponseStatusLike>

    @POST("api/movies/{id}/like")
    suspend fun LikeMovie(@Path("id") id: Int): Response<Unit>

    @DELETE("api/movies/{id}/like")
    suspend fun DislikeMovie(@Path("id") id: Int): Response<Unit>

    @POST("api/auth/register")
    suspend fun Register(@Body register: RequestRegister): Response<ResponseToken>

    @POST("api/auth/register")
    suspend fun Login(@Body login: RequestLogin): Response<ResponseToken>

}