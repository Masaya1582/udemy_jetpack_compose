package com.example.mypullrefresh

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class NumberViewModel: ViewModel() {
	private val _numbers = MutableStateFlow<List<Int>>(listOf())
	val numbers: StateFlow<List<Int>> = _numbers

	private val _isRefreshing = MutableStateFlow(false)
	val isRefreshing: StateFlow<Boolean> = _isRefreshing

	init {
		initializeNumbers()
	}

	//初期化時
	private fun initializeNumbers() {
		updateNumbers()
	}

	//PullToRefresh時
	fun refreshNumbers() {
		updateNumbers(isRefreshing = true)
	}

	//共通処理
	private fun updateNumbers(isRefreshing: Boolean = false) {
		viewModelScope.launch {
			if (isRefreshing) {
				_isRefreshing.emit(true)
			}

			val newList = List(5) { Random.nextInt(100) }
			_numbers.emit(newList)
			delay(1000)
			_isRefreshing.emit(false)
		}
	}
}
