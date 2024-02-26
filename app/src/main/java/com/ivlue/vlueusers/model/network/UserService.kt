package com.ivlue.vlueusers.model.network

import com.ivlue.vlueusers.model.network.entities.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("api/")
    fun getUsers(
        @Query("page") page: Int,
        @Query("results") results: Int,
        @Query("seed") seed: String
        ): Call<UserResponse>
}