package com.example.mynoteapp.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mynoteapp.components.SaveNoteButton
import com.example.mynoteapp.components.NoteInputTextField
import com.example.mynoteapp.data.NoteDataSource
import com.example.mynoteapp.model.Note
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
	notes: List<Note>,
	onAddNote: (Note) -> Unit,
	onRemoveNote: (Note) -> Unit
	) {
	var title by remember {
		mutableStateOf("")
	}
	var description by remember {
		mutableStateOf("")
	}
	val context = LocalContext.current
	Column(
		modifier = Modifier.padding(6.dp)
	) {
		TopAppBar(
			title = {
				Text(text = "Note App")
			},
			navigationIcon = {
				Icon(
					imageVector = Icons.Rounded.Menu,
					contentDescription = "Menu Icon",
					tint = Color.White
				)
			},
			colors = TopAppBarDefaults.topAppBarColors(
				containerColor = Color(0xFF3DC20D),
				titleContentColor = Color.White
			)
		)
		Column(
			modifier = Modifier.fillMaxWidth(),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			// タイトルフィールド
			NoteInputTextField(
				modifier = Modifier.padding(
					top = 8.dp,
					bottom = 8.dp
				),
				text = title,
				label = "タイトル",
				onTextChange = {
					if (it.all { char ->
						char.isLetter() || char.isWhitespace()
						}) title = it
				}
			)

			// 内容フィールド
			NoteInputTextField(
				modifier = Modifier.padding(
					top = 8.dp,
					bottom = 8.dp
				),
				text = description,
				label = "内容",
				onTextChange = {
					if (it.all { char ->
							char.isLetter() || char.isWhitespace()
						}) description = it
				}
			)

			// 保存ボタン
			SaveNoteButton(
				modifier = Modifier
					.padding(top = 8.dp)
					.width(240.dp)
					.height(44.dp),
				text = "保存する",
				enabled = (title.isNotEmpty() && description.isNotEmpty()),
				onClick = {
					onAddNote(Note(title = title, description = description))
					Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
					title = ""
					description = ""
				}
			)
		}
		Divider(modifier = Modifier.padding(12.dp))
		LazyColumn {
			items(notes) { note ->
				NoteItemCell(
					note = note,
					onNoteClicked = {
						onRemoveNote(note)
					}
				)
			}
		}
	}
}

@Composable
fun NoteItemCell(
	modifier: Modifier = Modifier,
	note: Note,
	onNoteClicked: (Note) -> Unit
) {
	Surface(
		modifier = Modifier
			.padding(4.dp)
			.clip(RoundedCornerShape(12.dp))
			.fillMaxWidth(),
		color = Color(0xFFE7EAEC),
		shadowElevation = 20.dp
	) {
		Column(
			modifier
				.clickable { onNoteClicked(note) }
				.padding(horizontal = 16.dp, vertical = 8.dp),
			horizontalAlignment = Alignment.Start
		) {
			Text(
				text = note.title,
				style = MaterialTheme.typography.bodyMedium
			)
			Text(
				text = note.description,
				style = MaterialTheme.typography.bodySmall
			)
			Text(
				text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")),
				style = MaterialTheme.typography.bodySmall
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
	NoteScreen(
		notes = NoteDataSource.loadNotes(),
		onAddNote = {},
		onRemoveNote = {}
	)
}
