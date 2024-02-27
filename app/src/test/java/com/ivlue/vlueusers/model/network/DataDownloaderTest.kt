package com.ivlue.vlueusers.model.network

import com.ivlue.vlueusers.model.network.entities.Info
import com.ivlue.vlueusers.model.network.entities.User
import com.ivlue.vlueusers.model.network.entities.UserResponse
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class DataDownloaderTest {
    @Test
    fun test_getUsers() = runBlocking{
        val apiService = mockk<UserService>()
        val apiClient = mockk<ApiClient>()
        val downloader = DataDownloader()

        val apiResponse = UserResponse(emptyList(), Info("seed", 10, 1, "version"))

        val mockedCall = mockk<Call<UserResponse>>()

        // Mock the behavior of ApiService methods using MockK's coEvery
        every { apiService.getUsers(any(), any(), any()) } returns mockedCall

        // Mock the enqueue behavior of the mockedCall
        every { mockedCall.enqueue(any()) } answers {
            val callback = args[0] as Callback<UserResponse>
            callback.onResponse(mockk(), Response.success(UserResponse(emptyList(), Info("seed", 10, 1, "version"))))
        }

        every { apiClient.apiService } returns apiService

        val result = downloader.getUsers(1, 10, "seed", apiClient)
        assertEquals(emptyList< User>(), result)
    }
}