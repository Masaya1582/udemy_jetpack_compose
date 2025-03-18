package com.example.movieapp.screens.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
	navController: NavController
) {
	Scaffold(
		topBar = {
			TopAppBar(
				colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Magenta),
				title = {
					Text(text = "Movies")
				}
			)
		}
	) { paddingValues ->
		Box(modifier = Modifier.padding(paddingValues)){
			MainContent(navController = navController)
		}
	}
}

@Composable
private fun MainContent(
	navController: NavController,
	movieLists: List<Movie> = getMovies()
) {
	Column(
		modifier = Modifier.padding(12.dp)
	) {
		LazyColumn {
			items(items = movieLists) {
				MovieRow(movie = it) { movie ->
					navController.navigate(route = MovieScreens.DetailsScreen.name+"/$movie")
				}
			}
		}
	}
}