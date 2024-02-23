package com.ivlue.vlueusers.model.network

import android.util.Log
import com.ivlue.vlueusers.model.network.entities.User
import com.ivlue.vlueusers.model.network.entities.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataDownloader {
    private val tag = "DataDownloader"

    fun getUsers(): Flow<List<User>> {
        val call = ApiClient.apiService.getUsers()
        val data = MutableStateFlow<List<UserResponse>>(emptyList())

        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                Log.d(tag, "server connected at: " + call.request().url)

                if (response.isSuccessful) {
                    val users = response.body()
                    val apiList = data.value ?: emptyList()
                    val updatedList = mutableListOf<UserResponse>()

                    apiList.let { updatedList.addAll(it) }
                    users.let { updatedList.addAll(it) }
                    data.value = updatedList
                } else {
                    Log.d(tag, "HTTP error code: " + response.code() + ", message: " + response.message())
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d(tag, "downloadUsers - call failed against url: " + call.request().url)
                call.cancel()
            }
        })

        return data
    }
}