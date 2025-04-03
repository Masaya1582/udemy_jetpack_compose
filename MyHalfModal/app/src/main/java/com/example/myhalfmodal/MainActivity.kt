package com.example.myhalfmodal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myhalfmodal.ui.theme.MyHalfModalTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			MyHalfModalTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					PasswordGeneratorView(
						modifier = Modifier
							.padding(innerPadding)
					)
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordGeneratorView(modifier: Modifier = Modifier) {
	var password by remember { mutableStateOf("") }
	var passwordLength by remember { mutableStateOf(12) }
	var includeUppercase by remember { mutableStateOf(true) }
	var includeLowercase by remember { mutableStateOf(true) }
	var includeNumbers by remember { mutableStateOf(true) }
	var includeSymbols by remember { mutableStateOf(true) }
	val clipboardManager = LocalClipboardManager.current

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp),
		verticalArrangement = Arrangement.spacedBy(8.dp)
	) {
		Text("Password Generator", style = MaterialTheme.typography.displaySmall)
		Row(verticalAlignment = Alignment.CenterVertically) {
			Text("Length: $passwordLength")
			Slider(
				value = passwordLength.toFloat(),
				onValueChange = { passwordLength = it.toInt() },
				valueRange = 8f..32f
			)
		}
		PasswordCheckBoxView(includeUppercase, includeLowercase, includeNumbers, includeSymbols)
		Button(onClick = {
			password = generatePassword(
				length = passwordLength,
				includeUppercase = includeUppercase,
				includeLowercase = includeLowercase,
				includeNumbers = includeNumbers,
				includeSymbols = includeSymbols
			)
		}) {
			Text("Generate Password")
		}
		if (password.isNotEmpty()) {
			GeneratedPasswordDetailView(password, clipboardManager)
		}
	}
}

@Composable
private fun GeneratedPasswordDetailView(
	password: String,
	clipboardManager: ClipboardManager
) {
	Row(verticalAlignment = Alignment.CenterVertically) {
		OutlinedTextField(
			value = password,
			onValueChange = {},
			readOnly = true,
			modifier = Modifier.weight(1f)
		)
		IconButton(onClick = {
			clipboardManager.setText(AnnotatedString(password))
		}) {
			Icon(
				imageVector = Icons.Filled.Share,
				contentDescription = null
			)
		}
	}
	Text("Strength: ${getPasswordStrength(password)}")
}

@Composable
private fun PasswordCheckBoxView(
	includeUppercase: Boolean,
	includeLowercase: Boolean,
	includeNumbers: Boolean,
	includeSymbols: Boolean
) {
	var includeUppercase1 = includeUppercase
	var includeLowercase1 = includeLowercase
	var includeNumbers1 = includeNumbers
	var includeSymbols1 = includeSymbols
	Row {
		Checkbox(
			checked = includeUppercase1,
			onCheckedChange = { includeUppercase1 = it }
		)
		Text(text = "Uppercase")
	}
	Row {
		Checkbox(
			checked = includeLowercase1,
			onCheckedChange = { includeLowercase1 = it }
		)
		Text(text = "Lowercase")
	}
	Row {
		Checkbox(
			checked = includeNumbers1,
			onCheckedChange = { includeNumbers1 = it }
		)
		Text(text = "Numbers")
	}
	Row {
		Checkbox(
			checked = includeSymbols1,
			onCheckedChange = { includeSymbols1 = it }
		)
		Text(text = "Symbols")
	}
}

private fun generatePassword(
	length: Int,
	includeUppercase: Boolean,
	includeLowercase: Boolean,
	includeNumbers: Boolean,
	includeSymbols: Boolean,
): String {
	val uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	val lowercaseChars = "abcdefghijklmnopqrstuvwxyz"
	val numberChars = "0123456789"
	val symbolChars = "!@#$%^&*()-_=+[]{}|;:'\",.<>/?`~"

	var allowedChars = ""
	if (includeUppercase) allowedChars += uppercaseChars
	if (includeLowercase) allowedChars += lowercaseChars
	if (includeNumbers) allowedChars += numberChars
	if (includeSymbols) allowedChars += symbolChars

	if (allowedChars.isEmpty()) return ""

	return (1..length)
		.map { allowedChars[Random.nextInt(allowedChars.length)] }
		.joinToString("")
}

private fun getPasswordStrength(password: String): String {
	val strength = when {
		password.length < 8 -> "Weak"
		password.length < 12 -> "Medium"
		else -> "Strong"
	}
	return strength
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	MyHalfModalTheme {
		PasswordGeneratorView()
	}
}
