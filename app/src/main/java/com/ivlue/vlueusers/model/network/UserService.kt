package com.ivlue.vlueusers.model.network

import com.ivlue.vlueusers.model.network.entities.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface UserService {
    @GET("?results=100")
    fun getUsers(): Call<UserResponse>
}