package com.example.apppractice.app_navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apppractice.uix.screens.LoginUserSolid
import com.example.apppractice.uix.screens.MenuMain
import com.example.apppractice.uix.screens.PracticeMaps

@Composable
fun AppNavControl() {

    val navController = rememberNavController()
    val modifier: Modifier = Modifier

    NavHost(navController = navController,
        modifier = modifier,
        startDestination = AppScreens.MenuMain.route) {

        composable(AppScreens.MenuMain.route) {
            MenuMain(modifier, navController)
        }

        composable(AppScreens.LoginUserSolid.route) {
            LoginUserSolid(modifier, navController)
        }

        composable(AppScreens.PracticeMaps.route) {
            PracticeMaps(modifier, navController)
        }

        /*composable(AppScreens.EncuestaPan.route + "/{name_sucursal}",
            arguments = listOf(
                navArgument(name = "name_sucursal") {
                    type = NavType.StringType
                }
            )
        ) {
                backStackEntry ->
            val nameSuc = backStackEntry.arguments?.getString("name_sucursal")?: ""
            EncuestaPan(navController, nameSuc)
        }

        composable(AppScreens.EncuestaSucursales.route) {
            EncuestaSucursales()
        }
        composable(AppScreens.CatalogSucursales.route) {
            CatalogSucursales()
        }*/
    }
}