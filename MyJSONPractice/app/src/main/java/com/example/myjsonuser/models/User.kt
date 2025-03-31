package com.example.myjsonuser.models

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class User(
    val results: List<ResultsItem>,
    val info: Info
)

@Serializable
data class Timezone(
    val offset: String,
    val description: String
)

@Serializable
data class Street(
    val number: Int,
    val name: String
)

@Serializable
data class ResultsItem(
    val nat: String,
    val gender: String,
    val phone: String,
    val dob: Dob,
    val name: Name,
    val registered: Registered,
    val location: Location,
    val id: Id,
    val login: Login,
    val cell: String,
    val email: String,
    val picture: Picture
)

@Serializable
data class Registered(
    val date: String,
    val age: Int
)

@Serializable
data class Picture(
    val thumbnail: String,
    val large: String,
    val medium: String
)

@Serializable
data class Name(
    val last: String,
    val title: String,
    val first: String
)

@Serializable
data class Login(
    val sha1: String,
    val password: String,
    val salt: String,
    val sha256: String,
    val uuid: String,
    val username: String,
    val md5: String
)

@Serializable
data class Location(
    val country: String,
    val city: String,
    val street: Street,
    val timezone: Timezone,
    @Serializable(with = PostcodeSerializer::class) val postcode: String?,
    val coordinates: Coordinates,
    val state: String
)

@Serializable
data class Info(
    val seed: String,
    val page: Int,
    val results: Int,
    val version: String
)

@Serializable
data class Id(
    val name: String,
    val value: String?
)

@Serializable
data class Dob(
    val date: String,
    val age: Int
)

@Serializable
data class Coordinates(
    val latitude: String,
    val longitude: String
)

object PostcodeSerializer : KSerializer<String?> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Postcode", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: String?) {
        encoder.encodeString(value ?: "")
    }

    override fun deserialize(decoder: Decoder): String? {
        return try {
            decoder.decodeInt().toString() // Int を String に変換
        } catch (e: SerializationException) {
            decoder.decodeString() // 文字列としてデコード
        }
    }
}
