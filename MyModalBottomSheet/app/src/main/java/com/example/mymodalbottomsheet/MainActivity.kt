package com.example.mymodalbottomsheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mymodalbottomsheet.ui.theme.MyModalBottomSheetTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			MyModalBottomSheetTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					ModalSheetExample(
						modifier = Modifier.padding(innerPadding)
					)
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ModalSheetExample(modifier: Modifier = Modifier) {
	val sheetState = rememberModalBottomSheetState()
	val scope = rememberCoroutineScope()
	var showBottomSheet by remember { mutableStateOf(false) }
	Scaffold(
		floatingActionButton = {
			ExtendedFloatingActionButton(
				text = { Text("Show bottom sheet") },
				icon = { Icon(Icons.Filled.Add, contentDescription = "") },
				onClick = {
					showBottomSheet = true
				}
			)
		}
	) { contentPadding ->
		Box(
			modifier = Modifier.fillMaxSize(),
			contentAlignment = Alignment.Center
		) {
			Column(modifier = Modifier.padding(contentPadding)) {
				Text("ここにメインコンテンツ")
			}
		}

		if (showBottomSheet) {
			ModalBottomSheet(
				onDismissRequest = {
					showBottomSheet = false
				},
				shape = BottomSheetDefaults.ExpandedShape,
				sheetState = sheetState
			) {
				Column(
					modifier = Modifier.padding()
				) {
					Image(
						painterResource(id = R.drawable.img_android),
						contentDescription = "Android Image",
						contentScale = ContentScale.Fit,
						modifier = Modifier
							.fillMaxWidth()
							.padding(horizontal = 8.dp)
					)
					Spacer(modifier = Modifier.padding(8.dp))
					Button(
						modifier = Modifier
							.fillMaxWidth()
							.padding(horizontal = 8.dp),
						onClick = {
							scope.launch { sheetState.hide() }.invokeOnCompletion {
								if (!sheetState.isVisible) {
									showBottomSheet = false
								}
							}
						}) {
						Text("Hide bottom sheet")
					}
				}
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	MyModalBottomSheetTheme {
		ModalSheetExample()
	}
}
