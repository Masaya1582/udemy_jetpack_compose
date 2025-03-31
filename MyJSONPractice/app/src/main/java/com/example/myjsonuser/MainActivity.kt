package com.example.myjsonuser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myjsonuser.models.Coordinates
import com.example.myjsonuser.models.Dob
import com.example.myjsonuser.models.Id
import com.example.myjsonuser.models.Location
import com.example.myjsonuser.models.Login
import com.example.myjsonuser.models.Name
import com.example.myjsonuser.models.Picture
import com.example.myjsonuser.models.Registered
import com.example.myjsonuser.models.ResultsItem
import com.example.myjsonuser.models.Street
import com.example.myjsonuser.models.Timezone
import com.example.myjsonuser.models.dummyUser
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
private fun UserListScreen(viewModel: UserViewModel) {
	val users by viewModel.users
	val isLoading by viewModel.isLoading
	val errorMessage by viewModel.errorMessage

	Box(
		modifier = Modifier.fillMaxSize(),
		contentAlignment = Alignment.Center
	) {
		Card(
			modifier = Modifier
				.fillMaxWidth(0.9f)
				.height(480.dp)
				.shadow(12.dp),
			shape = RoundedCornerShape(16.dp)
		) {
			Column(
				modifier = Modifier.fillMaxWidth(),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				if (isLoading) {
					CircularProgressIndicator(modifier = Modifier.padding(16.dp))
				} else if (errorMessage != null) {
					ErrorView(viewModel, errorMessage)
				} else {
					UserItem(user = users[0], viewModel = viewModel)
				}
			}
		}
	}
}

@Composable
private fun ErrorView(
	viewModel: UserViewModel,
	errorMessage: String?
) {
	Column(
		modifier = Modifier.padding(16.dp)
	) {
		Button(
			modifier = Modifier
				.padding(top = 12.dp)
				.align(Alignment.CenterHorizontally),
			onClick = {
				viewModel.fetchUsers()
			}
		) {
			Text("Try Again")
		}
		Text(
			"Error: ${errorMessage}",
			modifier = Modifier.padding(16.dp)
		)
	}
}

@Composable
private fun UserItem(user: ResultsItem, viewModel: UserViewModel) {
	Column(
		modifier = Modifier.padding(12.dp)
	) {
		AsyncImage(
			model = user.picture.large,
			contentDescription = "User Picture",
			alignment = Alignment.Center,
			contentScale = ContentScale.Crop,
			modifier = Modifier
				.padding(8.dp)
				.size(200.dp)
				.clip(CircleShape)
				.align(Alignment.CenterHorizontally) // AsyncImage だけ Center
		)
		Text("Name: ${user.name.first} ${user.name.last}")
		Text("Email: ${user.email}")
		Text("Phone: ${user.phone}")
		Text("Location: ${user.location.city}, ${user.location.country}")
		Button(
			modifier = Modifier
				.padding(top = 12.dp)
				.align(Alignment.CenterHorizontally),
			onClick = { viewModel.fetchUsers() }
		) {
			Text("Fetch Another User Info")
		}
	}
}

@Preview(showBackground = true)
@Composable
fun UserItemPreview() {
	val dummyUser = dummyUser
	val viewModel = UserViewModel()

	MyJSONUserTheme {
		UserItem(user = dummyUser, viewModel = viewModel)
	}
}
