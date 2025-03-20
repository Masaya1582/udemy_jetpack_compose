package com.example.mynoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mynoteapp.data.NoteDataSource
import com.example.mynoteapp.model.Note
import com.example.mynoteapp.screens.NoteScreen
import com.example.mynoteapp.ui.theme.MyNoteAppTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MyNoteAppTheme {
				Surface(modifier = Modifier.fillMaxSize()) {
					val notes = remember {
						mutableStateListOf<Note>()
					}
					NoteScreen(
						notes = notes,
						onAddNote = {
							notes.add(it)
						},
						onRemoveNote = {
							notes.remove(it)
						}
					)
				}
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	MyNoteAppTheme {
	}
}
