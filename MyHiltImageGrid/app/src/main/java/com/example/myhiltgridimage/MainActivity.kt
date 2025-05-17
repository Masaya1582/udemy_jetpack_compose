package com.example.myhiltgridimage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myhiltgridimage.data.ImageItem
import com.example.myhiltgridimage.ui.theme.MyHiltGridImageTheme
import com.example.myhiltgridimage.viewmodels.ImageGridViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	private val viewModel: ImageGridViewModel by viewModels()

	@OptIn(ExperimentalMaterial3Api::class)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MyHiltGridImageTheme {
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					val imageList by viewModel.images.collectAsState()
					
					Scaffold(
						topBar = {
							TopAppBar(title = { Text("Picsum 画像グリッド") })
						}
					) { paddingValues ->
						ImageGridScreen(
							images = imageList,
							modifier = Modifier.padding(paddingValues)
						)
					}
				}
			}
		}
	}
}

@Composable
fun ImageGridScreen(
	images: List<ImageItem>,
	modifier: Modifier = Modifier
) {
	if (images.isEmpty()) {
		// データがない場合はメッセージを表示（ローディング表示などにしても良い）
		Text("画像データを読み込み中...", modifier = modifier.padding(16.dp))
		return
	}

	LazyVerticalGrid(
		columns = GridCells.Fixed(3),
		modifier = modifier
			.fillMaxSize()
			.padding(4.dp),
		verticalArrangement = Arrangement.spacedBy(4.dp),
		horizontalArrangement = Arrangement.spacedBy(4.dp)
	) {
		items(
			items = images, key = { image -> image.id}
		) { imageItem ->
			ImageGridItem(item = imageItem)
		}
	}
}

@Composable
fun ImageGridItem(
	item: ImageItem,
	modifier: Modifier = Modifier
) {
	AsyncImage(
		model = ImageRequest.Builder(LocalContext.current)
			.data(item.url)
			.crossfade(true)
			.build(),
		contentDescription = "Image from Picsum (ID: ${item.id})",
		contentScale = ContentScale.Crop,
		modifier = Modifier
			.aspectRatio(1f)
			.fillMaxSize()
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	MyHiltGridImageTheme {
		val dummyImages = List(6) { index ->
			ImageItem(id = "dummy_$index", url = "https://picsum.photos/seed/preview${index}/200/300")
		}
		Scaffold(
			topBar = {
				TopAppBar(title = { Text("Picsum 画像グリッド (Preview)") })
			}
		) { paddingValues ->
			ImageGridScreen(
				images = dummyImages,
				modifier = Modifier.padding(paddingValues)
			)
		}
	}
}
