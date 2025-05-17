package com.example.myhiltgridimage.viewmodels

import android.media.Image
import androidx.lifecycle.ViewModel
import com.example.myhiltgridimage.data.ImageItem
import com.example.myhiltgridimage.repositories.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ImageGridViewModel @Inject constructor(
	private val imageRepository: ImageRepository
): ViewModel() {
	private val _images = MutableStateFlow<List<ImageItem>>(emptyList())
	val images: StateFlow<List<ImageItem>> = _images.asStateFlow()

	init {
		loadImages()
	}

	private fun loadImages() {
		_images.value = imageRepository.getImages()
	}
}
