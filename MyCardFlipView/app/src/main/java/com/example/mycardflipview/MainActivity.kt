package com.example.mycardflipview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mycardflipview.ui.theme.MyCardFlipViewTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			MyCardFlipViewTheme {
				Surface(modifier = Modifier.fillMaxSize()) {
					SamplePersonImageSlideView()
				}
			}
		}
	}
}

data class Person(val name: String, val imageUrl: String)

@Composable
fun PersonImageSlideView(persons: List<Person>) {
	Box(
		modifier = Modifier.fillMaxSize(),
		contentAlignment = Alignment.Center
	) {
		LazyRow(
			horizontalArrangement = Arrangement.spacedBy(16.dp), // Space between each card
			contentPadding = PaddingValues(horizontal = 16.dp) // Padding around the entire row
		) {
			// 'items' takes our list of 'Person' objects and creates a Composable for each one.
			items(persons) { person ->
				// Each 'person' becomes a 'Card', our photo frame.
				Card(
					modifier = Modifier.width(200.dp), // Fixed width for each card
				) {
					// Inside the Card, we arrange the image and the name vertically.
					Column(horizontalAlignment = Alignment.CenterHorizontally) {
						// AsyncImage is like a special picture frame that can load images from the internet.
						AsyncImage(
							model = person.imageUrl,
							contentDescription = person.name, // For accessibility
							modifier = Modifier
								.fillMaxWidth()
								.height(150.dp), // Fixed height for the image
							contentScale = ContentScale.Crop // Makes the image fill the space without distortion
						)
						// Text is like the caption under the photo.
						Text(
							text = person.name,
							modifier = Modifier.padding(8.dp) // Some padding around the name
						)
					}
				}
			}
		}
	}
}

@Composable
fun SamplePersonImageSlideView() {
	val dummyPersons = listOf(
		Person("Alice", "https://avatar.iran.liara.run/public/boy"),
		Person("Bob", "https://avatar.iran.liara.run/public/boy"),
		Person("Charlie", "https://avatar.iran.liara.run/public/boy"),
		Person("David", "https://avatar.iran.liara.run/public/boy"),
		Person("Eve", "https://avatar.iran.liara.run/public/boy"),
		Person("Frank", "https://avatar.iran.liara.run/public/boy"),
		Person("Grace", "https://avatar.iran.liara.run/public/boy"),
		Person("Henry", "https://avatar.iran.liara.run/public/boy"),
		Person("Ivy", "https://avatar.iran.liara.run/public/boy"),
		Person("Jack", "https://avatar.iran.liara.run/public/boy")
	)
	PersonImageSlideView(persons = dummyPersons)
}
