package com.example.myhiltgridimage.repositories

import com.example.myhiltgridimage.data.ImageItem
import javax.inject.Inject
import kotlin.random.Random

class ImageRepositoryImpl @Inject constructor(): ImageRepository {
	override fun getImages(): List<ImageItem> {
		return List(20) { index ->
			val uniqueId = "image_${index}_${Random.nextInt()}"
			ImageItem(
				id = uniqueId,
				url = "https://picsum.photos/200/300?random=${index + 1}"
			)
		}
	}
}
