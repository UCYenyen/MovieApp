package com.example.movieappnew.data.container

import com.example.movieappnew.data.repository.MovieServerRepository
import com.example.movieappnew.data.service.MovieServerService
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieServerContainer {
    companion object{
        val BASE_IMG_URL = "http://27.112.77.56:8015/media/posters/";
        val BASE_URL = "http://27.112.77.56:8015/"
        val ACCESS_TOKEN = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiZmVybmFuZG9Ac3R1ZGVudC5jaXB1dHJhLmFjLmlkIiwiaWF0IjoxNzYxNjM2OTcyLCJleHAiOjE3NjE3MjMzNzJ9.ot62DqmqJw44rURLoKej06vk9wJl7qk2J6jgImA-xKodO-X3aF8pXx9HTBt12gdazfMU4Q6KErgZk5uTjJotWQ"
    }

    class AuthInterceptor(private val bearerToken: String) : Interceptor {
        override fun intercept(chain: okhttp3.Interceptor.Chain): Response {
//            kalau ga perlu token ga usah ada function ini yes
            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer $bearerToken")
                .build()
            return chain.proceed(newRequest)
        }
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(ACCESS_TOKEN))
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    private val retrofitService: MovieServerService by lazy {
        retrofit.create(MovieServerService::class.java)
    }

    val MovieServerRepository : MovieServerRepository by lazy {
        MovieServerRepository(retrofitService)
    }
}