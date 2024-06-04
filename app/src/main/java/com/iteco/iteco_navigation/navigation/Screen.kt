package com.iteco.iteco_navigation.navigation

sealed class Screen(val route: String){
     object MainScreen : Screen("main_screen")
     object DetailScreen: Screen("detail_screen")

    object AuthorizationScreen: Screen("authorization_screen")

    fun withArgs(vararg args: String): String{
        return  buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}