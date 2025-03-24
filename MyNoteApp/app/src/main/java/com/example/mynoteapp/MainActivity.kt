package com.example.mynoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mynoteapp.data.NoteDataSource
import com.example.mynoteapp.model.Note
import com.example.mynoteapp.screens.NoteScreen
import com.example.mynoteapp.screens.NoteViewModel
import com.example.mynoteapp.ui.theme.MyNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MyNoteAppTheme {
				Surface(modifier = Modifier.fillMaxSize()) {
					val noteViewModel: NoteViewModel by viewModels()
					NotesApp(noteViewModel = noteViewModel)
				}
			}
		}
	}
}

@Composable
private fun NotesApp(
	noteViewModel: NoteViewModel = viewModel()
) {
	val notesList = noteViewModel.getAllNotes()
	NoteScreen(
		notes = notesList,
		onAddNote = {
			noteViewModel.addNote(it)
		},
		onRemoveNote = {
			noteViewModel.removeNote(it)
		}
	)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	MyNoteAppTheme {
	}
}
