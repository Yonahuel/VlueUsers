package com.ivlue.vlueusers.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object for managing Retrofit client configuration.
 */
object RetrofitClient {
    private const val BASE_URL = "https://randomuser.me/"

    /**
     * Lazy-initialized Retrofit instance.
     */
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

/**
 * Singleton object for managing the Retrofit API client.
 */
object ApiClient {
    /**
     * Lazy-initialized Retrofit service interface for user-related API endpoints.
     */
    val apiService: UserService by lazy {
        RetrofitClient.retrofit.create(UserService::class.java)
    }
}