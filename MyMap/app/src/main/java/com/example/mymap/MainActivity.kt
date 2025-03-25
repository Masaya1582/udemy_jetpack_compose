package com.example.mymap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mymap.ui.theme.MyMapTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			SettingsScreen()
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsScreen() {
	var notificationsEnabled by remember { mutableStateOf(true) }
	var username by remember { mutableStateOf("User123") }
	var selectedTheme by remember { mutableStateOf("ライト") }
	val themes = listOf("ライト", "ダーク", "システム設定")

	Scaffold(
		topBar = {
			TopAppBar(title = { Text("設定") })
		}
	) { padding ->
		Column(modifier = Modifier.padding(padding)) {
			// ユーザー情報セクション
			SettingsSection(title = "ユーザー情報") {
				SettingItem(title = "ユーザー名") {
					BasicTextField(
						value = username,
						onValueChange = { username = it },
						modifier = Modifier.fillMaxWidth()
					)
				}
			}

			// 通知セクション
			SettingsSection(title = "通知") {
				SettingItem(title = "プッシュ通知") {
					Switch(
						checked = notificationsEnabled,
						onCheckedChange = { notificationsEnabled = it }
					)
				}
			}

			// テーマセクション
			SettingsSection(title = "外観") {
				var expanded by remember { mutableStateOf(false) }

				SettingItem(title = "テーマ") {
					Box {
						Text(
							text = selectedTheme,
							modifier = Modifier
								.clickable { expanded = true }
								.padding(8.dp)
						)
						DropdownMenu(
							expanded = expanded,
							onDismissRequest = { expanded = false }
						) {
							themes.forEach { theme ->
								DropdownMenuItem(
									text = { Text(theme) },
									onClick = {
										selectedTheme = theme
										expanded = false
									}
								)
							}
						}
					}
				}
			}
		}
	}
}

// セクションヘッダー
@Composable
fun SettingsSection(title: String, content: @Composable ColumnScope.() -> Unit) {
	Column(modifier = Modifier.padding(16.dp)) {
		Text(text = title, style = MaterialTheme.typography.titleMedium)
		Spacer(modifier = Modifier.height(8.dp))
		Card(
			modifier = Modifier.fillMaxWidth(),
			elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
		) {
			Column(modifier = Modifier.padding(16.dp)) {
				content()
			}
		}
	}
}

// 設定アイテム
@Composable
fun SettingItem(title: String, content: @Composable RowScope.() -> Unit) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(vertical = 8.dp),
		horizontalArrangement = Arrangement.SpaceBetween
	) {
		Text(title)
		content()
	}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	MyMapTheme {
		SettingsScreen()
	}
}
