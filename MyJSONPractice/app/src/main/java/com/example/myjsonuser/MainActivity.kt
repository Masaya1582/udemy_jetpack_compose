package com.example.myjsonuser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myjsonuser.ui.theme.MyJSONUserTheme
import com.example.myjsonuser.viewmodels.UserViewModel

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MyJSONUserTheme {
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					val viewModel = UserViewModel()
					UserListScreen(viewModel = viewModel)
				}
			}
		}
	}
}

@Composable
fun UserListScreen(viewModel: UserViewModel) {
	val users by viewModel.users
	val isLoading by viewModel.isLoading
	val errorMessage by viewModel.errorMessage

	if (isLoading) {
		Text("Loading...")
	} else if (errorMessage != null) {
		Text("Error: ${errorMessage}")
	} else {
		LazyColumn {
			items(users) { user ->
				UserItem(user = user)
			}
		}
	}
}

@Composable
fun UserItem(user: com.example.myjsonuser.models.ResultsItem) {
	Column(modifier = Modifier.padding(16.dp)) {
		Text("Name: ${user.name.first} ${user.name.last}")
		Text("Email: ${user.email}")
		Text("Phone: ${user.phone}")
		Text("Location: ${user.location.city}, ${user.location.country}")
	}
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//	MyJSONUserTheme {
//		Greeting("Android")
//	}
//}
