package com.example.myiossettingview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myiossettingview.ui.theme.MyIOSSettingViewTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MyIOSSettingViewTheme {
				Surface() {
					SettingsView()
				}
			}
		}
	}
}

@Composable
private fun SettingsView() {
	Column(
		modifier = Modifier.fillMaxSize()
	) {
		SettingsSection(title = "General") {
			SettingsItem(title = "About")
			SettingsItem(title = "Accessibility")
			SettingsItem(title = "Privacy")
		}
		Divider()
		SettingsSection(title = "Accounts") {
			SettingsItem(title = "iCloud")
			SettingsItem(title = "Mail")
		}
	}
}

@Composable
private fun SettingsSection(
	title: String,
	content: @Composable ColumnScope.() -> Unit
) {
	Column(modifier = Modifier.fillMaxWidth()) {
		Text(
			text = title,
			style = MaterialTheme.typography.headlineMedium,
			modifier = Modifier.padding(16.dp)
		)
		content()
	}
}

@Composable
private fun SettingsItem(title: String) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		Text(text = title)
		Spacer(modifier = Modifier.weight(1f))
		Icon(Icons.Default.ArrowForward, contentDescription = "Navigate")
	}
	Divider()
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	MyIOSSettingViewTheme {
		SettingsView()
	}
}
