package com.example.movieapp.screens.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.viewmodels.FavoritesViewModel
import com.example.movieapp.widgets.FavoriteIcon
import com.example.movieapp.widgets.MovieRow


@Composable
fun HomeScreen(
    navController: NavController = rememberNavController(), viewModel: FavoritesViewModel

) {

    var showMenu by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Movies") },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                    }

                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(onClick = { /*TODO*/ navController.navigate(route = MovieScreens.FavoritesScreen.name) }) {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Favorites",
                                    modifier = Modifier.padding(4.dp)
                                )
                                Text(
                                    text = "Favorites",
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .width(100.dp)
                                )
                            }


                        }
                    }
                }
            )

        }
    ) {
        MainContent(
            navController = navController, favoritesViewModel = viewModel)
    }

}


@Composable
fun MainContent(
    navController: NavController,
    favoritesViewModel: FavoritesViewModel,
    movies: List<Movie> = getMovies()
) {

    LazyColumn {
        // item { Text(text = "Header") }
        items(movies) { movie ->
            MovieRow(
                movie = movie,
                onItemClick = { movieId ->
                    navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId")
                }
            ) {
                FavoriteIcon(
                    movie = movie,
                    isFavorite = favoritesViewModel.isFavorite(movie)
                ) { m ->  //callback event
                    if (favoritesViewModel.isFavorite(m)) {
                        favoritesViewModel.removeFromFavorites(m)
                    } else {
                        favoritesViewModel.addToFavorites(m)
                    }

                }

            }
        }

    }
}



