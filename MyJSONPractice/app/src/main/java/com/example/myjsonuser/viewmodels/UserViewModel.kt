package com.example.myjsonuser.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myjsonuser.models.ResultsItem
import com.example.myjsonuser.repository.RetrofitClient
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
	var users = mutableStateOf<List<ResultsItem>>(emptyList())
	var isLoading = mutableStateOf(false)
	var errorMessage = mutableStateOf<String?>(null)

	init {
		fetchUsers()
	}

	fun fetchUsers() {
		isLoading.value = true
		viewModelScope.launch {
			try {
				val response = RetrofitClient.apiService.getUsers()
				users.value = response.results ?: emptyList() // Corrected line
				isLoading.value = false
			} catch (e: Exception) {
				errorMessage.value = e.message
				isLoading.value = false
			}
		}
	}
}
