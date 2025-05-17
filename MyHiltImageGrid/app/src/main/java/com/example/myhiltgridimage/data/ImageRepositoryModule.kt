package com.example.myhiltgridimage.data

import com.example.myhiltgridimage.repositories.ImageRepository
import com.example.myhiltgridimage.repositories.ImageRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ImageRepositoryModule {

	@Binds
	@Singleton
	abstract fun bindImageRepository(
		imageRepositoryImpl: ImageRepositoryImpl
	): ImageRepository
}
