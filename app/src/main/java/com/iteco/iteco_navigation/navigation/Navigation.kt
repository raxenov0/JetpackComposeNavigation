package com.iteco.iteco_navigation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.iteco.iteco_navigation.api.ws.AuthorizationViewModel
import com.iteco.iteco_navigation.screens.Authorization.Authorization
import com.iteco.iteco_navigation.screens.DetailScreen
import com.iteco.iteco_navigation.screens.MainScreen

@Composable
fun Navigation(viewModel: AuthorizationViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.AuthorizationScreen.route){
        composable(route = Screen.AuthorizationScreen.route){
            Authorization(navController = navController, viewModel = viewModel)
        }

        composable(route = Screen.MainScreen.route){
            MainScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            route = Screen.DetailScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name"){
                    type = NavType.StringType
                    defaultValue = "Roman"
                    nullable = false
                }
            )
            ){ entry ->
            DetailScreen(navController = navController, name = entry.arguments?.getString("name"), viewModel = viewModel)
        }
    }
}


