package com.example.mynoteapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mynoteapp.R
import com.example.mynoteapp.components.NoteInputTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen() {
	Column(
		modifier = Modifier.padding(6.dp)
	) {
		TopAppBar(
			title = {
				Text(text = stringResource(R.string.jet_note))
			},
			actions = {
				Icon(
					imageVector = Icons.Rounded.Notifications,
					contentDescription = "Icon"
				)
			},
			colors = TopAppBarDefaults.topAppBarColors(
				containerColor = Color(0xFF0A89EE),
				titleContentColor = Color.White
			)
		)
		// Content
		Column(
			modifier = Modifier.fillMaxWidth(),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			NoteInputTextField(
				text = "Hello",
				label = "Hello",
				onTextChange = {}
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
	NoteScreen()
}
