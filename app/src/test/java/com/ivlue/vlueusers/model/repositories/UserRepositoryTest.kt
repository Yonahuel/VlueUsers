package com.ivlue.vlueusers.model.repositories

import com.ivlue.vlueusers.model.repositories.fake.remoteDataSource
import com.ivlue.vlueusers.model.network.DataDownloader
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class UserRepositoryTest {
    @Test
    fun test_getItems() = runBlocking{
        val dataDownloader = mockk<DataDownloader>()

        val users = remoteDataSource
        val repository = UserRepository(dataDownloader)

        coEvery { dataDownloader.getUsers(any(), any(), any()) } returns users

        val result = repository.getItems(1)

        val expected = Result.success(remoteDataSource)
        assertEquals(expected, result)
    }
}