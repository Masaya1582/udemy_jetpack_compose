package com.example.myfadeanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.myfadeanimation.ui.theme.MyFadeAnimationTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			MyFadeAnimationTheme {
				Surface() {
					MyFadeView()
				}
			}
		}
	}
}

@Composable
private fun MyFadeView() {
	var isShowFadeAnimationModal by remember { mutableStateOf(false) }

	Box(
		modifier = Modifier.fillMaxSize(),
		Alignment.Center
	) {
		Button(
			modifier = Modifier
				.width(320.dp)
				.height(48.dp),
			onClick = { isShowFadeAnimationModal = true }
		) {
			Text(text = "Show Fade Animation Modal View")
		}
	}

	if (isShowFadeAnimationModal) {
		ModalView {
			isShowFadeAnimationModal = false
		}
	}
}

@Composable
private fun ModalView(onDismiss: () -> Unit) {
	Dialog(
		onDismissRequest = onDismiss
	) {
		AnimatedVisibility(
			visible = true,
			enter = fadeIn(),
			exit = fadeOut()
		) {
			Box(
				modifier = Modifier
					.width(480.dp)
					.height(600.dp)
					.clip(RoundedCornerShape(16.dp))
					.background(Color.White),
				Alignment.Center
			) {
				Column(
					modifier = Modifier.padding(12.dp),
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					Text("Hello Animation Dialog")
					Box(
						modifier = Modifier
							.width(200.dp)
							.height(200.dp),
						Alignment.Center
					) {
						Image(
							painterResource(id = R.drawable.cuteboy), contentDescription = "Cute Dog"
						)
					}
					Button(onClick = onDismiss) {
						Text(text = "CLOSE")
					}
				}
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	MyFadeAnimationTheme {
		var isShowFadeAnimationModal by remember { mutableStateOf(false) }
		ModalView {
			isShowFadeAnimationModal = false
		}
	}
}
