package com.ivlue.vlueusers.model.network

import com.ivlue.vlueusers.model.network.entities.User
import com.ivlue.vlueusers.model.network.entities.UserResponse
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume

/**
 * This class handles the downloading of user data from the API.
 */
class DataDownloader {
    private val tag = "DataDownloader"
    /**
     * Fetches a list of users from the API.
     *
     * @param page The page number to retrieve.
     * @param results The number of results per page.
     * @param seed The seed value for the API request.
     * @param apiClient The [ApiClient] instance used to make the API call. Default is [ApiClient].
     * @return A list of [User] objects.
     */

    suspend fun getUsers(
        page: Int,
        results: Int,
        seed: String,
        apiClient: ApiClient = ApiClient
    ): List<User> {
        return suspendCancellableCoroutine { continuation ->
            val call = apiClient.apiService.getUsers(page = page, results = results, seed = seed)

            call.enqueue(object : Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    if (response.isSuccessful) {
                        val usersResponse = response.body()
                        val users = usersResponse?.results ?: emptyList()
                        continuation.resume(users)
                    } else {
                        continuation.resume(emptyList())
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    continuation.resume(emptyList())
                }
            })

            continuation.invokeOnCancellation {
                call.cancel()
            }
        }
    }
}