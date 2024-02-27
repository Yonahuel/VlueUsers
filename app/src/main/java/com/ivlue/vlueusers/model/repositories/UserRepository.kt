package com.ivlue.vlueusers.model.repositories

import com.ivlue.vlueusers.model.network.DataDownloader
import com.ivlue.vlueusers.model.network.entities.User

class UserRepository(private val downloader: DataDownloader) {
    suspend fun getItems(page: Int): Result<List<User>> {
        val users = downloader.getUsers(page, 10, "abc")

        return Result.success(users)
    }
}