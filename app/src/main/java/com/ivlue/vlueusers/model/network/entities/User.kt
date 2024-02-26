package com.ivlue.vlueusers.model.network.entities

import com.google.gson.annotations.SerializedName


data class UserResponse(
    @SerializedName("results") val results: List<User>,
    @SerializedName("info") val info: Info
)

data class User(
    @SerializedName("name") val name: UserName,
    @SerializedName("email") val email: String,
    @SerializedName("picture") val picture: UserProfilePicture,
    @SerializedName("phone") val phone: String,
    @SerializedName("location") val location: Location,
    @SerializedName("registered") val registered: Registered
)

data class Registered(
    @SerializedName("date") val date: String,
    @SerializedName("age") val age: Int
)

data class UserName(
    @SerializedName("title") val title: String,
    @SerializedName("first") val first: String,
    @SerializedName("last") val last: String
)

data class Location(
    @SerializedName("street") val street: Street,
    @SerializedName("city") val city: String,
    @SerializedName("state") val state: String,
    @SerializedName("country") val country: String,
    @SerializedName("postcode") val postcode: String,
    @SerializedName("coordinates") val coordinates: Coordinates,
    @SerializedName("timezone") val timezone: TimeZone
)

data class Street(
    @SerializedName("number") val number: Int,
    @SerializedName("name") val name: String,
)

data class Coordinates(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double
)

data class TimeZone(
    @SerializedName("offset") val offset: String,
    @SerializedName("description") val description: String
)

data class UserProfilePicture(
    @SerializedName("large") val large: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("thumbnail") val thumbnail: String
)

data class Info(
    @SerializedName("seed") val seed: String,
    @SerializedName("results") val results: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("version") val version: String
)
