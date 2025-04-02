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
    val results: List<ResultsItem>
)

@Serializable
data class ResultsItem(
    val phone: String,
    val name: Name,
    val location: Location,
    val email: String,
    val picture: Picture
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
data class Location(
    val country: String,
    val city: String,
)
