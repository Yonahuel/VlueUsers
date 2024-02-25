package com.ivlue.vlueusers.model.repositories

import com.ivlue.vlueusers.model.network.DataDownloader
import com.ivlue.vlueusers.model.network.entities.User
import com.ivlue.vlueusers.model.network.entities.UserName
import com.ivlue.vlueusers.model.network.entities.UserProfilePicture

class UserRepository(private val downloader: DataDownloader) {

    private val remoteDataSource = (1..100).map {
        User(
            UserName(
                first = "Nahuel",
                last = "Cueto"
            ),
            email = "yonahuel2006@gmail.com",
            picture = UserProfilePicture(
                large = "https://randomuser.me/api/portraits/men/75.jpg",
                medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
                thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg"
            )
        )
    }

    private suspend fun getUsers(): List<User> {
        val result = mutableListOf<User>()
        downloader.getUsers().collect { users ->
            result.addAll(users)
        }
        return result
    }

    suspend fun getItems(page: Int, pageSize: Int): Result<List<User>> {

        val startIndex = page * pageSize
        val users = remoteDataSource
        return if (startIndex + pageSize <= users.size) {
            Result.success(
                users.slice(startIndex until startIndex + pageSize)
            )
        } else Result.success(emptyList())
    }
}