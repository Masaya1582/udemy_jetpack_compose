package com.example.mycounterview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycounterview.ui.theme.MyCounterViewTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			MyCounterViewTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					CounterScreen(
						modifier = Modifier.padding(innerPadding)
					)
				}
			}
		}
	}
}

@Composable
fun CounterScreen(
	modifier: Modifier = Modifier
) {
	var count by remember { mutableStateOf(0) }

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			text = "Count: $count",
			fontSize = 48.sp,
			modifier = Modifier.padding(bottom = 32.dp)
		)
		Spacer(modifier = Modifier.height(32.dp))
		Button(
			onClick = {
				count ++
			},
			modifier = Modifier
				.fillMaxWidth()
				.height(60.dp)
		) {
			Text(
				text = "Increment Count",
				fontSize = 24.sp
			)
		}
		Spacer(modifier = Modifier.height(32.dp))
		Button(
			onClick = {
				count --
			},
			modifier = Modifier
				.fillMaxWidth()
				.height(60.dp),
			colors = ButtonDefaults.filledTonalButtonColors()
		) {
			Text(
				text = "Decrement Count",
				fontSize = 24.sp
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	MyCounterViewTheme {
		CounterScreen()
	}
}
