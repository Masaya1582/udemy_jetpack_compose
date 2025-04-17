package com.example.myinstagramview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myinstagramview.ui.theme.MyInstagramViewTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MyInstagramViewTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					InstagramHomeView(modifier = Modifier.padding(innerPadding))
				}
			}
		}
	}
}

data class User(
	val userName: String,
	val profilePic: Int
)

data class Post(
	val user: User,
	val media: List<Int>,
	val caption: String,
	val likes: Int
)

@Composable
private fun InstagramHomeView(modifier: Modifier = Modifier) {
	val posts = remember {
		listOf(
			Post(
				User("user_one", R.drawable.img_portrait1),
				listOf(R.drawable.landscape_1, R.drawable.landscape_2),
				"Enjoying a beautiful day!",
				150
			),
			Post(
				User("compose_lover", R.drawable.img_portrait1),
				listOf(R.drawable.landscape_3, R.drawable.landscape_4),
				"Learning Jetpack Compose is fun!",
				235
			),
			Post(
				User("android_dev", R.drawable.img_portrait1),
				listOf(R.drawable.landscape_5),
				"Coding time!",
				98
			),
			// Add more dummy posts here
		)
	}

	LazyColumn(
		modifier = Modifier.fillMaxSize()
	) {
		items(posts) { post ->
			InstagramPostItem(post = post)
			Divider(color = Color.LightGray, thickness = 1.dp)
		}
	}
}

@Composable
private fun InstagramPostItem(post: Post) {
	Column(
		modifier = Modifier.padding(8.dp)
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween,
			modifier = Modifier.fillMaxWidth()
		) {
			Row(
				verticalAlignment = Alignment.CenterVertically
			) {
				Image(
					painter = painterResource(id = post.user.profilePic),
					contentDescription = "Profile Picture",
					modifier = Modifier
						.size(32.dp)
						.clip(CircleShape),
					contentScale = ContentScale.Crop
				)
				Spacer(modifier = Modifier.width(8.dp))
				Text(text = post.user.userName, fontWeight = FontWeight.Bold)
			}
			Icon(Icons.Filled.MoreVert, contentDescription = "Options")
		}
		Spacer(modifier = Modifier.height(8.dp))

		LazyRow {
			items(post.media) { mediaId ->
				Image(
					painter = painterResource(id = mediaId),
					contentDescription = "Post Image",
					modifier = Modifier
						.fillMaxWidth()
						.height(300.dp),
					contentScale = ContentScale.Fit
				)
				Spacer(modifier = Modifier.width(8.dp))
			}
		}
		Spacer(modifier = Modifier.height(8.dp))
		Row(
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween,
			modifier = Modifier.fillMaxWidth()
		) {
			Row(verticalAlignment = Alignment.CenterVertically) {
				Icon(Icons.Filled.Favorite, contentDescription = "Like", tint = Color.Red)
				Spacer(modifier = Modifier.width(4.dp))
				Text(text = "${post.likes} likes", fontSize = 12.sp)
			}
			// You can add more action icons here (Comment, Share, etc.)
		}
		Spacer(modifier = Modifier.height(4.dp))
		Text(text = post.caption)
	}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	MyInstagramViewTheme {
		InstagramHomeView()
	}
}
