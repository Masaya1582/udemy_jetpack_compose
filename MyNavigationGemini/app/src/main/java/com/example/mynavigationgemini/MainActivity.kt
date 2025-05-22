package com.example.mynavigationgemini

import android.os.Bundle
import android.telecom.Call.Details
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mynavigationgemini.ui.theme.MyNavigationGeminiTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			MyNavigationGeminiTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					MyAppNavigation(
						modifier = Modifier.padding(innerPadding)
					)
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppNavigation(modifier: Modifier = Modifier) {
	val navController = rememberNavController()

	Scaffold(
		topBar = {
			CenterAlignedTopAppBar(
				title = { Text("Compose Navigation デモ") },
				navigationIcon = {
					val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
					if (currentRoute != NavRoutes.HOME_SCREEN && currentRoute != null) {
						IconButton(
							onClick = {
								navController.popBackStack()
							}
						) {
							Icon(
								imageVector = Icons.AutoMirrored.Filled.ArrowBack,
								contentDescription = "Back"
							)
						}
					}
				}
			)
		}
	) { paddingValues ->
		NavHost(
			navController = navController,
			startDestination = NavRoutes.HOME_SCREEN,
			modifier = Modifier.padding(paddingValues)
		) {
			composable(NavRoutes.HOME_SCREEN) {
				HomeScreen(navController = navController)
			}

			composable(
				NavRoutes.DETAIL_SCREEN,
				arguments = listOf(navArgument("userName") { type = NavType.StringType })
			) { backStackEntry ->
				val userName = backStackEntry.arguments?.getString("userName") ?: "ゲスト"
				DetailScreen(userName = userName)
			}
		}
	}
}

@Composable
fun HomeScreen(navController: NavController) {
	// remember と mutableStateOf で入力テキストの状態を保持
	var nameInput by remember { mutableStateOf("") }

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(24.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Text(text = "ユーザー名を入力してください", fontSize = 24.sp)
		Spacer(modifier = Modifier.height(32.dp))

		TextField(
			value = nameInput,
			onValueChange = { nameInput = it },
			label = { Text("ユーザー名") },
			modifier = Modifier.fillMaxWidth()
		)
		Spacer(modifier = Modifier.height(32.dp))

		Button(
			onClick = {
				navController.navigate(NavRoutes.createDetailRoute(nameInput))
			},
			// 入力がない場合はボタンを無効化
			enabled = nameInput.isNotBlank(),
			modifier = Modifier.fillMaxWidth()
		) {
			Text("詳細画面へ", fontSize = 20.sp)
		}
	}
}

@Composable
fun DetailScreen(userName: String) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(24.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Text(text = "こんにちは、", fontSize = 24.sp)
		Spacer(modifier = Modifier.height(16.dp))
		Text(text = "$userName さん！", fontSize = 36.sp, color = MaterialTheme.colorScheme.primary)
	}
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//	MyNavigationGeminiTheme {
//		Greeting("Android")
//	}
//}
