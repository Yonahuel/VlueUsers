package com.ivlue.vlueusers.model.network

import android.util.Log
import com.ivlue.vlueusers.model.network.entities.User
import com.ivlue.vlueusers.model.network.entities.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class DataDownloader {
    private val tag = "DataDownloader"

    fun getUsers(): Flow<List<User>> {
        val data = MutableStateFlow<List<User>>(emptyList())
        val call = ApiClient.apiService.getUsers()

        try {
            call.enqueue(object : Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    Log.d(tag, "server connected at: " + call.request().url)

                    if (response.isSuccessful) {
                        val usersResponse = response.body()
                        if (usersResponse != null) {
                            val users = usersResponse.results
                            data.value = users
                        } else {
                            Log.d(tag, "Empty response")
                        }
                    } else {
                        Log.d(tag, "HTTP error code: " + response.code() + ", message: " + response.message())
                    }
                }
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d(tag, "downloadUsers - call failed against url: " + call.request().url)
                    call.cancel()
                }
            })
        } catch (e: HttpException) {
            Log.d(tag, "Unknown http error: ${e.message}")
        } catch (e: Exception) {
            Log.d(tag, "Unknown error: ${e.message}")
        }
        return data
    }
}