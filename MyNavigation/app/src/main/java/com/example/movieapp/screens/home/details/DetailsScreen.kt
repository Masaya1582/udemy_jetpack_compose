package com.example.movieapp.screens.home.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {
	val newMovieList = getMovies().filter { movie ->
		movie.id == movieId
	}
	Scaffold(
		topBar = {
			TopAppBar(
				colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Magenta),
				title = {
					Text(text = "Details")
				},
				navigationIcon = {
					IconButton(onClick = { navController.popBackStack() }) {
						Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow Back")
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
				MovieRow(
					movie = newMovieList.first()
				)
				Spacer(modifier = Modifier.height(8.dp))
				Divider()
				Text(text = "Movie Images")
				HorizontalScrollableImageView(newMovieList)
			}
		}
	}
}

@Composable
private fun HorizontalScrollableImageView(newMovieList: List<Movie>) {
	LazyRow {
		items(newMovieList[0].images) { imageUrl ->
			Card(
				modifier = Modifier
					.padding(12.dp)
					.size(240.dp),
				elevation = CardDefaults.cardElevation(5.dp)
			) {
				Image(
					painter = rememberImagePainter(data = imageUrl),
					contentDescription = "Movie Images",
					contentScale = ContentScale.Crop
				)
			}
		}
	}
}
