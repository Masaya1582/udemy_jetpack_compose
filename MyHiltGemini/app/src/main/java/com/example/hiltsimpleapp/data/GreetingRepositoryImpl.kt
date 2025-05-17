package com.example.hiltsimpleapp.data

import java.lang.reflect.Constructor
import javax.inject.Inject

class GreetingRepositoryImpl @Inject constructor(): GreetingRepository {
	override fun getGreeting(): String {
		return "Hello Hilt! (2025/05/17)"
	}
}
