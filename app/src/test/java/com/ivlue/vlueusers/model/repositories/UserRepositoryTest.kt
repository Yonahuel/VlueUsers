package com.ivlue.vlueusers.model.repositories

import com.ivlue.vlueusers.model.repositories.fake.remoteDataSource
import com.ivlue.vlueusers.model.network.DataDownloader
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit test class for UserRepository.
 */
class UserRepositoryTest {
    /**
     * Test case for the getItems method.
     */
    @Test
    fun test_getItems() = runBlocking{
        // Mocking dependencies
        val dataDownloader = mockk<DataDownloader>()

        // Prepare fake data
        val users = remoteDataSource

        // Create UserRepository instance
        val repository = UserRepository(dataDownloader)

        // Mock the behavior of DataDownloader.getUsers method
        coEvery { dataDownloader.getUsers(any(), any(), any()) } returns users

        // Invoke the method under test
        val result = repository.getItems(1)

        // Define expected result
        val expected = Result.success(remoteDataSource)

        // Assert the result
        assertEquals(expected, result)
    }
}