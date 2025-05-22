package com.example.mynavigationgemini

object NavRoutes {
	const val HOME_SCREEN = "home_screen"
	const val DETAIL_SCREEN = "detail_screen/{userName}"

	fun createDetailRoute(userName: String) = "detail_screen/$userName"
}
