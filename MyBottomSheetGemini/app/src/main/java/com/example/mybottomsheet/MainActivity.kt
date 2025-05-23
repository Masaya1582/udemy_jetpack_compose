package com.example.mybottomsheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mybottomsheet.ui.theme.MyBottomSheetTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			MyBottomSheetTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					MainScreenWithBottomSheet(
						modifier = Modifier.padding(innerPadding)
					)
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenWithBottomSheet(modifier: Modifier = Modifier) {
	val sheetState = rememberModalBottomSheetState(
		skipPartiallyExpanded = false
	)
	val scope = rememberCoroutineScope()
	var showBottomSheet by remember { mutableStateOf(false) }

	Scaffold(
		topBar = {
			CenterAlignedTopAppBar(title = { Text("Modal Bottom Sheet Demo") })
		}
	) { paddingValues ->
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues)
				.padding(16.dp),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			Text(
				text = "Hello Bottom Sheet",
				fontSize = 32.sp
			)
			Spacer(modifier = Modifier.height(48.dp))
			Button(
				onClick = {
					showBottomSheet = true
				},
				modifier = Modifier.fillMaxWidth()
			) {
				Text("モーダルシートを表示", fontSize = 20.sp)
			}
		}

		if (showBottomSheet) {
			ModalBottomSheet(
				onDismissRequest = {
					showBottomSheet = false
				},
				sheetState = sheetState
			) {
				BottomSheetContent(
					onCloseClick = {
						scope.launch {
							sheetState.hide()
							showBottomSheet = false
						}
					}
				)
			}
		}
	}
}

@Composable
fun BottomSheetContent(onCloseClick: () -> Unit) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(24.dp)
			.navigationBarsPadding(), // ナビゲーションバー（下部のシステムバー）のパディングを自動調整
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Text(
			text = "これはモーダルシートです！",
			fontSize = 24.sp,
			modifier = Modifier.padding(bottom = 16.dp)
		)
		Text(
			text = "ここにシートに表示したいコンテンツを記述します。",
			fontSize = 16.sp,
			modifier = Modifier.padding(bottom = 32.dp)
		)
		Button(
			onClick = onCloseClick, // 親から渡されたコールバックを実行
			modifier = Modifier.fillMaxWidth()
		) {
			Text("シートを閉じる", fontSize = 18.sp)
		}
	}
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun PreviewMainScreenWithBottomSheet() {
	MainScreenWithBottomSheet()
}

@Preview(showBackground = true, widthDp = 360, heightDp = 300)
@Composable
fun PreviewBottomSheetContent() {
	BottomSheetContent(onCloseClick = {})
}
