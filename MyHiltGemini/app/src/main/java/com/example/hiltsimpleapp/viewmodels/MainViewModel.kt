package com.example.hiltsimpleapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.hiltsimpleapp.data.GreetingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	private val greetingRepository: GreetingRepository
): ViewModel() {
	val greetingMessage: String

	init {
		greetingMessage = greetingRepository.getGreeting()
	}
}
