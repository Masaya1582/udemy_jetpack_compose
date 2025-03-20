package com.example.mynoteapp.data

import com.example.mynoteapp.model.Note

class NoteDataSource {
	companion object {
		fun loadNotes(): List<Note> {
			return listOf(
				Note(title = "A good day", description = "We went on a vacation yesterday"),
				Note(title = "Grocery List", description = "Milk, eggs, bread, cheese"),
				Note(title = "Meeting Notes", description = "Discussed project timelines and deliverables"),
				Note(title = "Ideas for App", description = "Implement user authentication and data storage"),
				Note(title = "Book Recommendations", description = "The Hitchhiker's Guide to the Galaxy, 1984"),
				Note(title = "Workout Routine", description = "3 sets of 10 push-ups, 3 sets of 12 squats"),
				Note(title = "Recipe: Chocolate Cake", description = "Ingredients: flour, sugar, cocoa powder, eggs..."),
				Note(title = "Travel Plans", description = "Book flights to Tokyo for next month"),
				Note(title = "Coding Tips", description = "Remember to use proper variable naming conventions"),
				Note(title = "Daily Journal", description = "Today I learned about state hoisting in Jetpack Compose"),
				Note(title = "Project Brainstorm", description = "New UI designs and feature ideas."),
				Note(title = "Reminder: Doctor's Appointment", description = "Checkup at 3 PM on Friday.")
			)
		}
	}
}
