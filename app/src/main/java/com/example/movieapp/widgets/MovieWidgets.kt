package com.example.movieapp.widgets

import androidx.compose.foundation.Image
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies


@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun MovieRow(
    movie: Movie = getMovies()[0],
    onItemClick: (String) -> Unit = {},
    content: @Composable () -> Unit = {}
) {

    var showMoreInfo by remember {
        mutableStateOf(false)
    }


    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            //height(230.dp)
            //.height(170.dp)
            //.fillMaxSize()
            .clickable {
                onItemClick(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .padding(12.dp)
            ) {
                //Icon(imageVector = Icons.Default.AccountBox, contentDescription = "movie pic")
                /* Image(
                    painter = rememberImagePainter(
                        data = movie.images[0],
                        builder = {
                            transformations(CircleCropTransformation())
                        }),
                    contentDescription = "Movie pic",
                )
                */

                //contentScale = ContentScale.Crop,
                //modifier = Modifier.clip(CircleShape)
                AsyncImage(
                     model = ImageRequest.Builder(LocalContext.current)
                         .data(data = movie.images[0])
                         .crossfade(true)
                         .build(),
                     contentDescription = "Movie pic",
                     contentScale = ContentScale.Crop,
                     modifier = Modifier.clip(CircleShape)
                 )

            }
            Column {
                // Spacer(modifier = Modifier.height(8.dp))
                Text(text = movie.title, style = MaterialTheme.typography.h5)
                Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.caption)
                Text(text = "Released: ${movie.year}", style = MaterialTheme.typography.caption)




                AnimatedVisibility(visible = showMoreInfo) {
                    Column {
                        Text(text = "Plot: ${movie.plot}", style = MaterialTheme.typography.caption)

                        Divider(modifier = Modifier.padding(4.dp))
                        Text(
                            text = "Genre: ${movie.genre}",
                            style = MaterialTheme.typography.caption
                        )
                        Text(
                            text = "Actors: ${movie.actors}",
                            style = MaterialTheme.typography.caption
                        )
                        Text(
                            text = "Rating: ${movie.rating}",
                            style = MaterialTheme.typography.caption
                        )
                    }

                }

                IconButton(onClick = { showMoreInfo = !showMoreInfo }) {
                    if (showMoreInfo) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "arrow Down"
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "arrow Up"
                        )
                    }
                }


            }
            content()

        }

    }

}

@Composable
fun HorizontalScrollableImageView(movie: Movie = getMovies()[0]) {
    LazyRow {
        items(movie.images) { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = 4.dp
            ) {
                /* Image(
                     painter = rememberImagePainter(data = image),
                     contentDescription = "movie image"
                 )
                 */
                 AsyncImage(
                     model = ImageRequest.Builder(LocalContext.current)
                         .data(data = image)
                         .build(),
                     contentDescription = "Movie image"
                 )
            }
        }

    }
}

@Composable
fun FavoriteIcon(
    movie: Movie, isFavorite: Boolean = false,
    favoriteClickedON: (Movie) -> Unit = {}
) {


    // Column() {
   Row(horizontalArrangement = Arrangement.End) {

        IconButton(
            modifier = Modifier.width(80.dp),
            onClick = { /*TODO*/ favoriteClickedON(movie) }

        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Default.Favorite
                else Icons.Default.FavoriteBorder,
                contentDescription = "add to favorites",
                tint = Color.Magenta

            )


            /*  if (addFavoriteMovie) {
                  Icon(
                      imageVector = Icons.Default.Favorite,
                      contentDescription = "Favorite clicked"
                  )
              } else {
                  Icon(
                      imageVector = Icons.Default.FavoriteBorder,
                      contentDescription = "Favorite empty"
                  )
              }*/

        }
   }


}
