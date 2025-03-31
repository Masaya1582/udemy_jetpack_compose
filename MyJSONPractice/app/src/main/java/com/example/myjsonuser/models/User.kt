package com.example.myjsonuser.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val results: List<ResultsItem>,
    val info: Info
)

@Serializable
data class Timezone(
    val offset: String = "",
    val description: String = ""
)

@Serializable
data class Street(
    val number: Int,
    val name: String = ""
)

@Serializable
data class ResultsItem(
    val nat: String = "",
    val gender: String = "",
    val phone: String = "",
    val dob: Dob,
    val name: Name,
    val registered: Registered,
    val location: Location,
    val id: Id,
    val login: Login,
    val cell: String = "",
    val email: String = "",
    val picture: Picture
)

@Serializable
data class Registered(
    val date: String = "",
    val age: Int
)

@Serializable
data class Picture(
    val thumbnail: String = "",
    val large: String = "",
    val medium: String = ""
)

@Serializable
data class Name(
    val last: String = "",
    val title: String = "",
    val first: String = ""
)

@Serializable
data class Login(
    val sha1: String = "",
    val password: String = "",
    val salt: String = "",
    val sha256: String = "",
    val uuid: String = "",
    val username: String = "",
    val md5: String = ""
)

@Serializable
data class Location(
    val country: String = "",
    val city: String = "",
    val street: Street,
    val timezone: Timezone,
    val postcode: Int,
    val coordinates: Coordinates,
    val state: String = ""
)

@Serializable
data class Info(
    val seed: String = "",
    val page: Int = 0,
    val results: Int = 0,
    val version: String = ""
)

@Serializable
data class Id(
    val name: String = "",
    val value: String = ""
)

@Serializable
data class Dob(
    val date: String = "",
    val age: Int = 0
)

@Serializable
data class Coordinates(
    val latitude: String = "",
    val longitude: String = ""
)
