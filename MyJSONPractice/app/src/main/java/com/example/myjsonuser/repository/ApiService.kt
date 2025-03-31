package com.example.myjsonuser.repository
import com.example.myjsonuser.models.User
import retrofit2.http.GET

interface ApiService {
	@GET("api/")
	suspend fun getUsers(): User
}
