package com.example.mynoteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mynoteapp.model.Note
import com.example.mynoteapp.util.DateConverter
import com.example.mynoteapp.util.UUIDConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase: RoomDatabase() {
	abstract fun noteDao(): NoteDatabaseDao
}
