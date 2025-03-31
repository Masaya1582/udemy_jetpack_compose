package com.example.myjsonuser.models

val dummyUser = ResultsItem(
nat = "US",
gender = "male",
phone = "+123456789",
dob = Dob(date = "1990-01-01", age = 34),
name = Name(title = "Mr", first = "John", last = "Doe"),
registered = Registered(date = "2010-06-15", age = 14),
location = Location(
country = "USA",
city = "New York",
street = Street(number = 123, name = "Main Street"),
timezone = Timezone(offset = "-5:00", description = "Eastern Time"),
postcode = "10001",
coordinates = Coordinates(latitude = "40.7128", longitude = "-74.0060"),
state = "New York"
),
id = Id(name = "SSN", value = "123-45-6789"),
login = Login(
sha1 = "sha1hash",
password = "password123",
salt = "salt123",
sha256 = "sha256hash",
uuid = "uuid-1234",
username = "johndoe",
md5 = "md5hash"
),
cell = "+987654321",
email = "john.doe@example.com",
picture = Picture(
thumbnail = "https://randomuser.me/api/portraits/thumb/men/1.jpg",
large = "https://randomuser.me/api/portraits/men/1.jpg",
medium = "https://randomuser.me/api/portraits/med/men/1.jpg"
)
)
