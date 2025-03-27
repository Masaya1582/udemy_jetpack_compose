package com.example.mypodcast

import android.graphics.Paint.Align
import android.media.Image
import android.os.Bundle
import android.widget.HorizontalScrollView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.mypodcast.ui.theme.MyPodcastTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			MyPodcastTheme {
				Surface() {
					MyPodcastView()
				}
			}
		}
	}
}

@Composable
private fun MyPodcastView() {
	val imageList = listOf(
		R.drawable.img_coffee,
		R.drawable.img_coffee,
		R.drawable.img_coffee,
		R.drawable.img_coffee
	)
	Column(
		modifier = Modifier
			.fillMaxSize()
			.verticalScroll(rememberScrollState())
	) {
		HeaderView()
		FeaturedView(imageList)
		TrendingView(imageList)
	}
}

@Composable
private fun TrendingView(imageList: List<Int>) {
	Column {
		Row(
			modifier = Modifier.padding(8.dp)
		) {
			Icon(
				modifier = Modifier
					.width(24.dp)
					.height(24.dp),
				imageVector = Icons.Default.Favorite, contentDescription = "HeadPhone Image"
			)
			Spacer(modifier = Modifier.padding(4.dp))
			Text(
				text = "Trending Now"
			)
		}
		imageList.chunked(2).forEach { rowImages -> // 'rowImages' is now List<Int>
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(vertical = 8.dp),
				horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
			) {
				rowImages.forEach { imageId -> // 'imageId' is now Int
					Image(
						painter = painterResource(id = imageId), // Correct usage
						contentDescription = "Coffee",
						modifier = Modifier
							.width(160.dp)
							.height(160.dp)
							.clip(RoundedCornerShape(12.dp)),
						contentScale = ContentScale.Crop
					)
				}
				if (rowImages.size == 1) {
					Spacer(modifier = Modifier.weight(1f))
				}
			}
		}
	}
}

@Composable
private fun FeaturedView(imageList: List<Int>) {
	Row(
		modifier = Modifier.padding(8.dp)
	) {
		Icon(
			modifier = Modifier
				.width(24.dp)
				.height(24.dp),
			imageVector = Icons.Default.Home, contentDescription = "HeadPhone Image"
		)
		Spacer(modifier = Modifier.padding(4.dp))
		Text(
			text = "Featured Podcasts"
		)
	}
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(8.dp)
			.horizontalScroll(rememberScrollState()),
		horizontalArrangement = Arrangement.spacedBy(8.dp)
	) {
		imageList.forEach { imageId ->
			Image(
				painterResource(id = imageId), contentDescription = "Coffee",
				modifier = Modifier
					.width(160.dp)
					.height(160.dp)
					.clip(RoundedCornerShape(12.dp)),
				contentScale = ContentScale.FillBounds
			)
		}
	}
}

@Composable
private fun HeaderView() {
	Row(
		modifier = Modifier.padding(8.dp)
	) {
		Icon(
			modifier = Modifier
				.width(24.dp)
				.height(24.dp),
			imageVector = Icons.Default.Phone, contentDescription = "HeadPhone Image"
		)
		Spacer(modifier = Modifier.padding(4.dp))
		Text(
			text = "Podcasts"
		)
	}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	MyPodcastTheme {
		MyPodcastView()
	}
}
