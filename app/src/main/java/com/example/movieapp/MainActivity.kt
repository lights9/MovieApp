
package com.example.movieapp


import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieapp.models.getMovies
import com.example.movieapp.navigation.MovieNavigation
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.viewmodels.FavoritesViewModel


class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MainActivity", "onCreate")

        val movies = getMovies()
        val vm: FavoritesViewModel by viewModels()


        val faves = vm.favoriteMovies //immutable list cant call functions like add,remove,..
        //faves. doesn't work

        //val faves = vm.getAllMovies()

        setContent {
            //val vm2: FavoritesViewModel by viewModel()
            MyApp {
                MovieNavigation() //HomeScreen() -> DetailScreen()

            }
        }
    }
    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart" )

    }
    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "onResume" )
    }

    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "onPause" )
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity", "onRestart" )
    }


    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "onStop" )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy" )
    }



}


@Composable
fun MyApp(content: @Composable () -> Unit) {
  MovieAppTheme {
      content()
  }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    MyApp {
        MovieNavigation()

    }


}