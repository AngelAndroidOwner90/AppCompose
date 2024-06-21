package com.example.apppractice.app_navigation

sealed class AppScreens(val route: String) {
    object MenuMain: AppScreens("menu_main_scn")
    object LoginUserSolid: AppScreens("login_user_scn")
    object PracticeMaps: AppScreens("maps_scn")
}