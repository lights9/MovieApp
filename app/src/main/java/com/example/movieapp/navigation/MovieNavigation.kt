package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.screens.detail.DetailScreen
import com.example.movieapp.screens.home.HomeScreen
import com.example.movieapp.screens.favorite.FavoritesScreen
import com.example.movieapp.viewmodels.FavoritesViewModel

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    val favoritesViewModel: FavoritesViewModel = viewModel()


    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name) {
        // composable("homescreen") { HomeScreen(navController = navController) }
        composable(MovieScreens.HomeScreen.name) {
            HomeScreen(navController = navController, viewModel = favoritesViewModel) }

        // url: www.domain.com/detailscreen/movie=12
        composable(
            route = MovieScreens.DetailScreen.name + "/{movie}",
            arguments = listOf(navArgument("movie") {
                type = NavType.StringType
            })
        ) { backStackEntry ->  //add more routes and screens here

            DetailScreen(
                navController = navController,
                favoritesViewModel,
                movieId = backStackEntry.arguments?.getString("movie")

            )
        }

        composable(route = MovieScreens.FavoritesScreen.name) {
           FavoritesScreen(navController = navController, viewModel = favoritesViewModel)
        }


    }


}
