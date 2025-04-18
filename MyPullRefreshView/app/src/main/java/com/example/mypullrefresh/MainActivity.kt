package com.example.mypullrefresh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mypullrefresh.ui.theme.MyPullRefreshTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			MyPullRefreshTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					PullToRefreshBasicSample(
						modifier = Modifier.padding(innerPadding)
					)
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullToRefreshBasicSample(
	modifier: Modifier = Modifier,
	imageViewModel: ImageViewModel = viewModel()
) {
	val images by imageViewModel.images.collectAsState()
	val isRefreshing by imageViewModel.isRefreshing.collectAsState()

	val state = rememberPullRefreshState(
		refreshing = isRefreshing,
		onRefresh = { imageViewModel.refreshImages() }
	)

	Box(modifier = modifier
		.fillMaxSize()
		.pullRefresh(state),
		contentAlignment = Alignment.Center
	) {
		LazyColumn(
			modifier = Modifier.fillMaxWidth(),
			contentPadding = PaddingValues(16.dp),
			verticalArrangement = Arrangement.spacedBy(12.dp)
		) {
			items(images) { imageRes ->
				Image(
					painterResource(id = imageRes),
					contentDescription = "Portrait Image",
					modifier = Modifier
						.fillMaxWidth()
						.height(200.dp)
						.clip(RoundedCornerShape(12.dp)),
					contentScale = ContentScale.Crop
				)
			}
		}
		PullRefreshIndicator(
			refreshing = isRefreshing,
			state = state,
			modifier = Modifier.align(Alignment.TopCenter)
		)
	}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	MyPullRefreshTheme {
		PullToRefreshBasicSample()
	}
}
