package com.example.movieapp.screens.home.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, movieData: String?) {
	Scaffold(
		topBar = {
			TopAppBar(
				colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Magenta),
				title = {
					Text(text = "Details")
				},
				navigationIcon = {
					IconButton(onClick = { navController.popBackStack() }) {
						androidx.compose.material3.Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back")
					}
				}
			)
		}
	) { _ ->
		Surface(
			modifier = Modifier
				.fillMaxWidth()
				.fillMaxHeight()
		) {
			Column(
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.Center
			) {
				Text(text = movieData.toString())
			}
		}
	}
}
