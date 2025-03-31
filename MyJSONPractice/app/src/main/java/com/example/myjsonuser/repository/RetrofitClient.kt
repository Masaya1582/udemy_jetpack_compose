package com.example.myjsonuser.repository

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object RetrofitClient {
	private const val BASE_URL = "https://randomuser.me/"

	private val json = Json {
		ignoreUnknownKeys = true
	}

	private val contentType = "application/json".toMediaType()

	@OptIn(ExperimentalSerializationApi::class)
	val apiService: ApiService by lazy {
		Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addConverterFactory(json.asConverterFactory(contentType))
			.build()
			.create(ApiService::class.java)
	}
}
