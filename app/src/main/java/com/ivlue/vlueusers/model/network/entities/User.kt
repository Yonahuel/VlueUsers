package com.ivlue.vlueusers.model.network.entities

import com.google.gson.annotations.SerializedName

/**
 * Represents the response received from the user-related API endpoints.
 * @property results The list of users returned in the response.
 * @property info Additional information about the response.
 */
data class UserResponse(
    @SerializedName("results") val results: List<User>,
    @SerializedName("info") val info: Info
)

/**
 * Represents a user entity.
 * @property name The name of the user.
 * @property email The email address of the user.
 * @property picture The profile picture URLs of the user.
 * @property phone The phone number of the user.
 * @property location The location information of the user.
 * @property registered The registration information of the user.
 */
data class User(
    @SerializedName("name") val name: UserName,
    @SerializedName("email") val email: String,
    @SerializedName("picture") val picture: UserProfilePicture,
    @SerializedName("phone") val phone: String,
    @SerializedName("location") val location: Location,
    @SerializedName("registered") val registered: Registered
)

/**
 * Represents the registration information of a user.
 * @property date The registration date of the user.
 * @property age The age of the user at the time of registration.
 */
data class Registered(
    @SerializedName("date") val date: String,
    @SerializedName("age") val age: Int
)

/**
 * Represents the name of a user.
 * @property title The title (e.g., Mr., Ms., Dr.) of the user's name.
 * @property first The first name of the user.
 * @property last The last name of the user.
 */
data class UserName(
    @SerializedName("title") val title: String,
    @SerializedName("first") val first: String,
    @SerializedName("last") val last: String
)

/**
 * Represents the location information of a user.
 * @property street The street information of the user's address.
 * @property city The city of the user's address.
 * @property state The state or region of the user's address.
 * @property country The country of the user's address.
 * @property postcode The postal code of the user's address.
 * @property coordinates The geographic coordinates of the user's address.
 * @property timezone The timezone information of the user's address.
 * */
data class Location(
    @SerializedName("street") val street: Street,
    @SerializedName("city") val city: String,
    @SerializedName("state") val state: String,
    @SerializedName("country") val country: String,
    @SerializedName("postcode") val postcode: String,
    @SerializedName("coordinates") val coordinates: Coordinates,
    @SerializedName("timezone") val timezone: TimeZone
)

/**
 * Represents the street information of a user's address.
 * @property number The street number.
 * @property name The name of the street.
 */
data class Street(
    @SerializedName("number") val number: Int,
    @SerializedName("name") val name: String,
)

/**
 * Represents the geographic coordinates of a location.
 * @property latitude The latitude coordinate.
 * @property longitude The longitude coordinate.
 */
data class Coordinates(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double
)

/**
 * Represents the timezone information of a location.
 * @property offset The timezone offset.
 * @property description The description of the timezone.
 */
data class TimeZone(
    @SerializedName("offset") val offset: String,
    @SerializedName("description") val description: String
)

/**
 * Represents the URLs for the user's profile picture.
 * @property large The URL for the large-sized profile picture.
 * @property medium The URL for the medium-sized profile picture.
 * @property thumbnail The URL for the thumbnail-sized profile picture.
 */
data class UserProfilePicture(
    @SerializedName("large") val large: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("thumbnail") val thumbnail: String
)

/**
 * Represents additional information about the API response.
 * @property seed The seed used to generate the response.
 * @property results The number of results in the response.
 * @property page The page number of the response.
 * @property version The version of the API.
 */
data class Info(
    @SerializedName("seed") val seed: String,
    @SerializedName("results") val results: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("version") val version: String
)