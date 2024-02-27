package com.ivlue.vlueusers.model.repositories

import com.ivlue.vlueusers.model.network.DataDownloader
import com.ivlue.vlueusers.model.network.entities.User

/**
 * Repository class responsible for managing user data.
 * @param downloader The [DataDownloader] used to fetch user data from the network.
 */
class UserRepository(private val downloader: DataDownloader) {
    /**
     * Fetches a list of users from the network.
     * @param page The page number of the results to fetch.
     * @return A [Result] object containing the list of users fetched from the network.
     */
    suspend fun getItems(page: Int): Result<List<User>> {
        // Call the DataDownloader to fetch users from the network
        val users = downloader.getUsers(page, 10, "abc")
        // Return the fetched users as a success result
        return Result.success(users)
    }
}