package com.example.mymoviereview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mymoviereview.ui.theme.MyMovieReviewTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MyMovieReviewTheme {
				Surface {
					MovieReviewViewPreview()
				}
			}
		}
	}
}

@Composable
fun MovieReviewView(
	title: String,
	imageResId: Int,
	starCount: Int,
	comments: List<String>
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.verticalScroll(rememberScrollState())
			.padding(16.dp)
	) {
		Column(horizontalAlignment = Alignment.CenterHorizontally) {
			Text(
				text = title,
				fontWeight = FontWeight.Bold,
				fontSize = 24.sp,
				modifier = Modifier.padding(bottom = 8.dp)
			)
			Image(
				painterResource(id = imageResId),
				contentDescription = "Movie Poster",
				modifier = Modifier
					.fillMaxWidth()
					.height(200.dp),
				contentScale = ContentScale.Crop
			)
		}
		Row(
			modifier = Modifier.padding(vertical = 8.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			repeat(starCount) {
				Icon(
					imageVector = Icons.Filled.Star,
					contentDescription = "Star",
					tint = MaterialTheme.colorScheme.secondary,
					modifier = Modifier.size(24.dp)
				)
			}
		}
		Text(
			text = "Comments",
			fontWeight = FontWeight.SemiBold,
			fontSize = 18.sp,
			modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
		)
		comments.forEach { comment ->
			Column {
				Text(
					text = comment,
					modifier = Modifier.padding(vertical = 4.dp)
				)
				Divider()
			}
		}
	}
}

@Composable
fun MovieReviewViewPreview() {
	val comments = listOf(
		"Great movie! Loved the acting.",
		"The plot was a bit slow, but overall enjoyable.",
		"Visually stunning!",
		"A must-watch for any fan of the genre.",
		"I was really disappointed with the ending.",
		"Amazing movie, one of the best I've seen in a long time!"
	)

	MovieReviewView(
		title = "Action Movie 2023",
		imageResId = R.drawable.movie_poster, // Replace with your image
		starCount = 4,
		comments = comments
	)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	MyMovieReviewTheme {
		MovieReviewViewPreview()
	}
}
