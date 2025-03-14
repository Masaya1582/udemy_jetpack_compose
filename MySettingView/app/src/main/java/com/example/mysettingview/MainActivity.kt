package com.example.mysettingview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.mysettingview.ui.theme.MySettingViewTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MySettingViewTheme {
				Surface(modifier = Modifier.fillMaxSize()) {
					SettingView()
				}
			}
		}
	}
}

@Composable
fun SettingView() {
	val scrollState = rememberScrollState()
	Column(
		modifier = Modifier
			.fillMaxSize()
			.verticalScroll(scrollState)
	) {
		MainProfile(name = "Android", profileImageResId = R.drawable.portrait)
		MainProfile(name = "Android", profileImageResId = R.drawable.portrait)
		MainProfile(name = "Android", profileImageResId = R.drawable.portrait)
		MainProfile(name = "Android", profileImageResId = R.drawable.portrait)
		MainProfile(name = "Android", profileImageResId = R.drawable.portrait)
		MainProfile(name = "Android", profileImageResId = R.drawable.portrait)
		MainProfile(name = "Android", profileImageResId = R.drawable.portrait)
		MainProfile(name = "Android", profileImageResId = R.drawable.portrait)
		MainProfile(name = "Android", profileImageResId = R.drawable.portrait)
		MainProfile(name = "Android", profileImageResId = R.drawable.portrait)
		MainProfile(name = "Android", profileImageResId = R.drawable.portrait)
		MainProfile(name = "Android", profileImageResId = R.drawable.portrait)
		MainProfile(name = "Android", profileImageResId = R.drawable.portrait)
		MainProfile(name = "Android", profileImageResId = R.drawable.portrait)
		MainProfile(name = "Android", profileImageResId = R.drawable.portrait)
		MainProfile(name = "Android", profileImageResId = R.drawable.portrait)
		MainProfile(name = "Android", profileImageResId = R.drawable.portrait)
		MainProfile(name = "Android", profileImageResId = R.drawable.portrait)
		MainProfile(name = "Android", profileImageResId = R.drawable.portrait)
		MainProfile(name = "Android", profileImageResId = R.drawable.portrait)
	}

}

@Composable
fun MainProfile(
	name: String,
	profileImageResId: Int,
) {
	Surface(
		modifier = Modifier
			.fillMaxWidth()
			.padding(8.dp),
		shape = RoundedCornerShape(16.dp),
		color = MaterialTheme.colorScheme.onBackground
	) {
		Row(
			modifier = Modifier.padding(16.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			Image(
				painter = painterResource(id = profileImageResId),
				contentDescription = "Profile Image",
				modifier = Modifier
					.size(60.dp)
					.clip(CircleShape),
				contentScale = ContentScale.Crop
			)
			Spacer(modifier = Modifier.width(16.dp))
			Text(
				text = name,
				color = Color.White,
				fontWeight = FontWeight.Bold,
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	MySettingViewTheme {
		SettingView()
	}
}
