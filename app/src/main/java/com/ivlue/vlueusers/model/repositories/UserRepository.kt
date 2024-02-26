package com.ivlue.vlueusers.model.repositories

import com.ivlue.vlueusers.model.network.DataDownloader
import com.ivlue.vlueusers.model.network.entities.Coordinates
import com.ivlue.vlueusers.model.network.entities.Location
import com.ivlue.vlueusers.model.network.entities.Registered
import com.ivlue.vlueusers.model.network.entities.Street
import com.ivlue.vlueusers.model.network.entities.TimeZone
import com.ivlue.vlueusers.model.network.entities.User
import com.ivlue.vlueusers.model.network.entities.UserName
import com.ivlue.vlueusers.model.network.entities.UserProfilePicture

class UserRepository(private val downloader: DataDownloader) {

    private val remoteDataSource = (1..100).map {
        User(
            name = UserName(
                title = "Mr.",
                first = "Nahuel",
                last = "Cueto"
            ),
            email = "yonahuel2006@gmail.com",
            picture = UserProfilePicture(
                large = "https://randomuser.me/api/portraits/men/75.jpg",
                medium = "https://randomuser.me/api/portraits/med/men/75.jpg",
                thumbnail = "https://randomuser.me/api/portraits/thumb/men/75.jpg"
            ),
            phone = "(272) 790-0888",
            location = Location(
                street = Street(
                    number = 8929,
                    name = "Valwood Pkwy"
                ),
                city = "Billings",
                state = "Michigan",
                country = "United States",
                postcode = "63104",
                coordinates = Coordinates(
                    latitude = -69.8246,
                    longitude = 134.8719
                ),
                timezone = TimeZone(
                    offset = "+9:30",
                    description = "Adelaide, Darwin"
                )
            ),
            registered = Registered(
                date = "2007-07-09T05:51:59.390Z",
                age = 14
            )
        )
    }

    private suspend fun getUsers(page: Int, seed: String): List<User> {
        val result = mutableListOf<User>()
        downloader.getUsers(page, 10, seed).collect { users ->
            result.addAll(users)
        }
        return result
    }

    suspend fun getItems(page: Int, pageSize: Int): Result<List<User>> {

        val startIndex = page * pageSize
        //val users = getUsers(page, "abc")
        val users = remoteDataSource
        return if (startIndex + pageSize <= users.size) {
            Result.success(
                users.slice(startIndex until startIndex + pageSize)
            )
        } else Result.success(emptyList())
    }
}