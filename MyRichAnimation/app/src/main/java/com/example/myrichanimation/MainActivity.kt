package com.example.myrichanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myrichanimation.models.Snack
import com.example.myrichanimation.ui.theme.MyRichAnimationTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MyRichAnimationTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					FavoriteSnackList()
				}
			}
		}
	}
}

private val favoriteSnacks = listOf(
	Snack("Chocolate Chip Cookies", "https://images.unsplash.com/photo-1551782450-a2132b4ba21d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8Y2hpcCUyMGNvb2tpZXN8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60"),
	Snack("Ice Cream Sundae", "https://images.unsplash.com/photo-1561043484-176967e74761?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8aWNlJTIwY3JlYW0lMjBzdW5kYWV8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60"),
	Snack("Pizza Slice", "https://images.unsplash.com/photo-1534308983491-686996fd0a20?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTF8fHBpenphJTIwc2xpY2V8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60"),
	Snack("Popcorn", "https://images.unsplash.com/photo-1560474968-399999076891?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fHBvcGNvcm58ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60"),
	Snack("Fruit Salad", "https://images.unsplash.com/photo-1492796540962-7ff0139155d7?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTF8fGZydWl0JTIwc2FsYWR8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60")
)

@Composable
fun SnackItem(
	modifier: Modifier = Modifier,
	snack: Snack
) {

	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		// Coil's AsyncImage to load the image from the URL
		AsyncImage(
			modifier = Modifier
				.width(120.dp)
				.height(120.dp),
			contentScale = ContentScale.Crop,
			model = snack.imageUrl,
			contentDescription = snack.name,
		)
		Text(text = snack.name)
	}
}

@Composable
private fun FavoriteSnackList() {
	LazyColumn {
		items(favoriteSnacks) { snack ->
			SnackItem(snack = snack)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	MyRichAnimationTheme {
		FavoriteSnackList()
	}
}
