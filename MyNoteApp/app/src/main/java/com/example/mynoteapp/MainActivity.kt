package com.example.mynoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mynoteapp.screens.NoteScreen
import com.example.mynoteapp.viewmodels.NoteViewModel
import com.example.mynoteapp.ui.theme.MyNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MyNoteAppTheme {
				Surface(modifier = Modifier.fillMaxSize()) {
					val noteViewModel = viewModel<NoteViewModel>()
					NotesApp(noteViewModel = noteViewModel)
				}
			}
		}
	}
}

@Composable
private fun NotesApp(noteViewModel: NoteViewModel) {
	val notesList = noteViewModel.noteList.collectAsState().value
	NoteScreen(
		notes = notesList,
		// 追加処理
		onAddNote = {
			noteViewModel.addNote(it)
		},
		// 削除処理
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
