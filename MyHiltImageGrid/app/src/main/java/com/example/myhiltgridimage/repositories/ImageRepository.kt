package com.example.myhiltgridimage.repositories

import com.example.myhiltgridimage.data.ImageItem

interface ImageRepository {
	// 今回は固定リストなので、ひとまずsuspendなしでシンプルに
	fun getImages(): List<ImageItem>
}
