package com.example.mypullrefresh

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class ImageViewModel: ViewModel() {
	private val _images = MutableStateFlow<List<Int>>(listOf())
	val images: StateFlow<List<Int>> = _images

	private val _isRefreshing = MutableStateFlow(false)
	val isRefreshing: StateFlow<Boolean> = _isRefreshing

	init {
		initializeImages()
	}

	//初期化時
	private fun initializeImages() {
		updateImages()
	}

	//PullToRefresh時
	fun refreshImages() {
		updateImages(isRefreshing = true)
	}

	//共通処理
	private fun updateImages(isRefreshing: Boolean = false) {
		viewModelScope.launch {
			if (isRefreshing) {
				_isRefreshing.emit(true)
			}

			val newList = List(5) {
				val randomIndex = Random.nextInt(1, 11)
				getDrawableResId(randomIndex)
			}
			_images.emit(newList)
			delay(1000)
			_isRefreshing.emit(false)
		}
	}

	@DrawableRes
	private fun getDrawableResId(index: Int): Int {
		return when (index) {
			1 -> R.drawable.img_portrait1
			2 -> R.drawable.img_portrait2
			3 -> R.drawable.img_portrait3
			4 -> R.drawable.img_portrait4
			5 -> R.drawable.img_portrait5
			6 -> R.drawable.img_portrait6
			7 -> R.drawable.img_portrait7
			8 -> R.drawable.img_portrait8
			9 -> R.drawable.img_portrait9
			10 -> R.drawable.img_portrait10
			else -> R.drawable.img_portrait1
		}
	}
}
