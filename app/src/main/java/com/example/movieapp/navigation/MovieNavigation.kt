package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.screens.detail.DetailScreen
import com.example.movieapp.screens.home.HomeScreen
import com.example.movieapp.screens.favorite.FavoritesScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name) {
        // composable("homescreen") { HomeScreen(navController = navController) }
        composable(MovieScreens.HomeScreen.name) {
            HomeScreen(navController = navController) }

        // url: www.domain.com/detailscreen/movie=12
        composable(
            MovieScreens.DetailScreen.name + "/{movie}",
            arguments = listOf(navArgument("movie") {
                type = NavType.StringType
            })
        ) { backStackEntry ->  //add more routes and screens here

            DetailScreen(
                navController = navController,
                movieId = backStackEntry.arguments?.getString("movie")
            )
        }

        composable(route = MovieScreens.FavoritesScreen.name) {
           FavoritesScreen(navController = navController)
        }


    }


}
