package com.ivlue.vlueusers.model.repositories

import com.ivlue.vlueusers.model.network.entities.User
import com.ivlue.vlueusers.model.network.entities.UserName
import com.ivlue.vlueusers.model.network.entities.UserProfilePicture

class UserRepository {
    private val remoteDataSource = (1..100).map {
        User(
            UserName(
                first = "Nahuel",
                last = "Cueto"
            ),
            email = "yonahuel2006@gmail.com",
            picture = UserProfilePicture(
                large = "",
                medium = "",
                thumbnail = ""
            )
        )
    }

    fun getItems(
        page: Int,
        pageSize: Int
    ): Result<List<User>> {
        val startIndex = page * pageSize
        return if (startIndex + pageSize <= remoteDataSource.size) {
            Result.success(
                remoteDataSource.slice(startIndex until startIndex + pageSize)
            )
        } else Result.success(emptyList())
    }
}