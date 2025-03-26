package com.example.mynoteapp.repository

import com.example.mynoteapp.data.NoteDatabaseDao
import com.example.mynoteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface NoteRepositoryInterface {
	suspend fun addNote(note: Note)
	suspend fun updateNote(note: Note)
	suspend fun deleteNote(note: Note)
	suspend fun deleteAllNotes()
	fun getAllNotes(): Flow<List<Note>>
}

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao): NoteRepositoryInterface {
	override suspend fun addNote(note: Note) = noteDatabaseDao.insert(note = note)
	override suspend fun updateNote(note: Note) = noteDatabaseDao.update(note)
	override suspend fun deleteNote(note: Note) = noteDatabaseDao.deleteNote(note)
	override suspend fun deleteAllNotes() = noteDatabaseDao.deleteAll()
	override fun getAllNotes(): Flow<List<Note>> = noteDatabaseDao.getNotes().flowOn(Dispatchers.IO).conflate()
}
