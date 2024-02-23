package com.ivlue.vlueusers.model.network.entities

import com.google.gson.annotations.SerializedName


data class UserResponse(
    @SerializedName("results") val results: List<User>,
    @SerializedName("info") val info: Info
)

data class User(
    @SerializedName("name") val name: UserName,
    @SerializedName("email") val email: String,
    @SerializedName("picture") val picture: UserProfilePicture
)

data class UserName(
    @SerializedName("first") val first: String,
    @SerializedName("last") val last: String
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
