package com.example.mynoteapp.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.mynoteapp.data.NoteDataSource
import com.example.mynoteapp.model.Note

class NoteViewModel: ViewModel() {
	private var noteList = mutableStateListOf<Note>()

	init {
		noteList.addAll(NoteDataSource.loadNotes())
	}

	fun addNote(note: Note) {
		noteList.add(note)
	}

	fun removeNote(note: Note) {
		noteList.remove(note)
	}

	fun getAllNotes(): List<Note> {
		return noteList
	}
}
