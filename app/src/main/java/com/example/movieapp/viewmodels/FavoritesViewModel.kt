package com.example.movieapp.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.movieapp.models.Movie

class FavoritesViewModel: ViewModel() {

    private val _favoriteMovies = mutableStateListOf<Movie>() //mutableListOfMovie
    val favoriteMovies: List<Movie>
      get() = _favoriteMovies


    //function instead of mutable list
   /* fun getAllMovies(): List<Movie> {
        return favoriteMovies
    }*/

    fun addToFavorites(movie: Movie){
        if (!exists(movie = movie)){
            _favoriteMovies.add(movie)
        }

    }

    fun removeFromFavorites(movie: Movie){
        _favoriteMovies.remove(movie)
    }

    private fun exists(movie: Movie) : Boolean{
      return _favoriteMovies.any() {m -> m.id == movie.id}
    }

    fun isFavorite(movie: Movie) : Boolean{
        return exists(movie)
    }




}