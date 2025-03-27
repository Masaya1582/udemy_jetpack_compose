package com.example.mypodcast.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypodcast.models.ImageItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ImageViewModel: ViewModel() {
	private val _images = MutableStateFlow<List<ImageItem>>(emptyList())
	val images: StateFlow<List<ImageItem>> get() = _images

	init {
		loadImages()
	}

	private fun loadImages() {
		viewModelScope.launch {
			val imageUrls = listOf(
				"https://picsum.photos/200",
				"https://picsum.photos/200",
				"https://picsum.photos/200",
				"https://picsum.photos/200",
				"https://picsum.photos/200",
				"https://picsum.photos/200",
				"https://picsum.photos/200",
				"https://picsum.photos/200",
				"https://picsum.photos/200",
				"https://picsum.photos/200",
				"https://picsum.photos/200",
				"https://picsum.photos/200",
				"https://picsum.photos/200",
				"https://picsum.photos/200",
				"https://picsum.photos/200",
				"https://picsum.photos/200",
				"https://picsum.photos/200",
				"https://picsum.photos/200",
				"https://picsum.photos/200",
			)
			_images.value = imageUrls.map { ImageItem(it) }
		}
	}
}
