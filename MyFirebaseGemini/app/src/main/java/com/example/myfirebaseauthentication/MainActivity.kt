package com.example.myfirebaseauthentication

import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myfirebaseauthentication.ui.theme.MyFirebaseAuthenticationTheme
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			MyFirebaseAuthenticationTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					FirebaseAuthApp(
						modifier = Modifier.padding(innerPadding)
					)
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirebaseAuthApp(modifier: Modifier = Modifier) {
	val navController = rememberNavController()
	val auth = remember { FirebaseAuth.getInstance() }
	val context = LocalContext.current

	var isLoggedIn by remember { mutableStateOf(auth.currentUser != null) }

	DisposableEffect(auth) {
		val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
			val user = firebaseAuth.currentUser
			isLoggedIn = (user != null)
			if (user != null) {
				navController.navigate(AuthNavRoutes.HOME_SCREEN) {
					popUpTo(AuthNavRoutes.AUTH_SCREEN) { inclusive = true }
				}
			}
		}
		auth.addAuthStateListener(authStateListener)
		onDispose {
			auth.removeAuthStateListener(authStateListener)
		}
	}

	Scaffold(
		topBar = {
			CenterAlignedTopAppBar(title = { Text("Firebase Auth Demo") })
		}
	) { paddingValues ->
		NavHost(
			navController = navController,
			startDestination = if (isLoggedIn) AuthNavRoutes.HOME_SCREEN else AuthNavRoutes.AUTH_SCREEN,
			modifier = Modifier.padding(paddingValues)
		) {
			composable(AuthNavRoutes.AUTH_SCREEN) {
				AuthScreen(
					auth = auth,
					onAuthSuccess = {
						// 認証成功時の処理はAuthStateListenerでハンドリングされるため、ここでは何もしない
					}
				)
			}
			composable(AuthNavRoutes.HOME_SCREEN) {
				HomeScreen(
					auth = auth,
					onLogout = {
						auth.signOut()
						Toast.makeText(context, "ログアウトしました", Toast.LENGTH_SHORT).show()
					}
				)
			}
		}
	}
}

@Composable
fun AuthScreen(
	auth: FirebaseAuth,
	onAuthSuccess: () -> Unit
) {
	var email by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }
	var isLoading by remember { mutableStateOf(false) }
	val context = LocalContext.current
	val scope = rememberCoroutineScope()

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(24.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Text(text = "Firebase認証", fontSize = 32.sp)
		Spacer(modifier = Modifier.height(32.dp))
		TextField(
			value = email,
			onValueChange = { email = it },
			label = { Text("メールアドレス") },
			modifier = Modifier.fillMaxWidth()
		)
		Spacer(modifier = Modifier.height(16.dp))
		TextField(
			value = password,
			onValueChange = { password = it },
			label = { Text("パスワード") },
			visualTransformation = PasswordVisualTransformation(),
			modifier = Modifier.fillMaxWidth()
		)
		Spacer(modifier = Modifier.height(32.dp))

		if (isLoading) {
			CircularProgressIndicator(modifier = Modifier.size(48.dp))
		} else {
			Button(
				onClick = {
					isLoading = true
					scope.launch {
						try {
							auth.createUserWithEmailAndPassword(email, password).await()
							Toast.makeText(context, "サインアップ成功",  Toast.LENGTH_SHORT).show()
							onAuthSuccess()
						} catch (e: Exception) {
							Toast.makeText(context, "サインアップ失敗: ${e.message}", Toast.LENGTH_LONG).show()
							e.printStackTrace()
						} finally {
							isLoading = false
						}
					}
				},
				enabled = email.isNotBlank() && password.isNotBlank(),
				modifier = Modifier.fillMaxWidth()
			) {
				Text("サインアップ", fontSize = 20.sp)
			}
			Spacer(modifier = Modifier.height(16.dp))
			Button(
				onClick = {
					isLoading = true
					scope.launch {
						try {
							auth.signInWithEmailAndPassword(email, password).await()
							Toast.makeText(context, "ログイン成功！", Toast.LENGTH_SHORT).show()
							onAuthSuccess()
						} catch (e: Exception) {
							Toast.makeText(context, "ログイン失敗: ${e.message}", Toast.LENGTH_LONG).show()
							e.printStackTrace() // エラー詳細をログに出力
						} finally {
							isLoading = false
						}
					}
				},
				enabled = email.isNotBlank() && password.isNotBlank(),
				modifier = Modifier.fillMaxWidth()
			) {
				Text("ログイン", fontSize = 20.sp)
			}
		}
	}
}

@Composable
fun HomeScreen(auth: FirebaseAuth, onLogout: () -> Unit) {
	val currentUser = auth.currentUser // 現在のログインユーザー情報を取得

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(24.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Text(text = "ログイン成功！", fontSize = 32.sp)
		Spacer(modifier = Modifier.height(16.dp))
		Text(
			text = "ようこそ、${currentUser?.email ?: "ゲスト"}さん！",
			fontSize = 24.sp
		)
		Spacer(modifier = Modifier.height(32.dp))
		Button(
			onClick = onLogout,
			modifier = Modifier.fillMaxWidth()
		) {
			Text("ログアウト", fontSize = 20.sp)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	MyFirebaseAuthenticationTheme {
		FirebaseAuthApp()
	}
}
