package com.example.myplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myplayground.ui.theme.MyPlaygroundTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MyPlaygroundTheme {
				val mockItem = listOf(
					MockItem(id = 1, name = "Japan"),
					MockItem(id = 1, name = "Japan"),
					MockItem(id = 1, name = "Japan"),
					MockItem(id = 1, name = "Japan"),
					MockItem(id = 1, name = "Japan"),
					MockItem(id = 1, name = "Japan")
				)
				Surface(
					modifier = Modifier
						.fillMaxWidth()
						.fillMaxHeight()
				) {
					ScrollableList(items = mockItem)
				}
			}
		}
	}
}

data class MockItem(
	val id: Int,
	val name: String
)

@Composable
fun ScrollableList(items: List<MockItem>) {
	val listState = rememberLazyListState()
	val mockItem = listOf(
		MockItem(id = 1, name = "Japan"),
		MockItem(id = 2, name = "America"),
		MockItem(id = 3, name = "France"),
		MockItem(id = 4, name = "Spain"),
		MockItem(id = 5, name = "India"),
		MockItem(id = 6, name = "China")
	)
	LazyColumn(state = listState) {
		items(mockItem) { item ->
			Text(text = item.name)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	MyPlaygroundTheme {
		val mockItem = listOf(
			MockItem(id = 1, name = "Japan"),
			MockItem(id = 1, name = "Japan"),
			MockItem(id = 1, name = "Japan"),
			MockItem(id = 1, name = "Japan"),
			MockItem(id = 1, name = "Japan"),
			MockItem(id = 1, name = "Japan")
		)
		ScrollableList(items = mockItem)
	}
}
