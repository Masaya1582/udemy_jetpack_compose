package com.example.myjsonuser.models

val dummyUser = ResultsItem(
	phone = "+123456789",
	name = Name(title = "Mr", first = "John", last = "Doe"),
	location = Location(
		country = "USA",
		city = "New York"
	),
	email = "john.doe@example.com",
	picture = Picture(
		thumbnail = "https://randomuser.me/api/portraits/thumb/men/1.jpg",
		large = "https://randomuser.me/api/portraits/men/1.jpg",
		medium = "https://randomuser.me/api/portraits/med/men/1.jpg"
	)
)
