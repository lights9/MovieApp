package com.example.movieapp.screens.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.widgets.MovieRow


@Preview(showBackground = true)
@Composable
fun FavoritesScreen(
    navController: NavController = rememberNavController()
) {

    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Magenta, elevation = 3.dp) {
                Row {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arrow back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()  // go back to last screen
                        })

                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "My Favorite Movies", style = MaterialTheme.typography.h6)
                }

            }

        }
    ) {
        MainContent(movieList = getMovies().subList(4,6))
    }
}

@Composable
fun MainContent(movieList: List<Movie>) {
    LazyColumn {
        items(movieList) { movie ->
            MovieRow(movie = movie){

            }
        }
    }
}
