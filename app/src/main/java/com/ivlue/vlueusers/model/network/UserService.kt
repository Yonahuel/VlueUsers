package com.ivlue.vlueusers.model.network

import com.ivlue.vlueusers.model.network.entities.UserResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface defining the user-related API endpoints.
 */
interface UserService {
    /**
     * Fetches a list of users from the API.
     * @param page The page number of the results.
     * @param results The number of results per page.
     * @param seed The seed used for generating random data.
     * @return A Retrofit [Call] object representing the asynchronous HTTP request.
     */
    @GET("api/")
    fun getUsers(
        @Query("page") page: Int,
        @Query("results") results: Int,
        @Query("seed") seed: String
        ): Call<UserResponse>
}