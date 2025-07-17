package com.example.movieapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.viewmodel.DetailsViewModel
import com.example.movieapp.viewmodel.FavouriteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    movieId: Int,
    navController: NavHostController,
    favViewModel: FavouriteViewModel,
    viewModel: DetailsViewModel = viewModel()
) {
    val movie by viewModel.movie.collectAsState()
    val isFavorite = viewModel.isFavorite

    LaunchedEffect(movieId) {
        viewModel.fetchMovieById(movieId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Movie Details", textAlign = TextAlign.Center) },
            )
        }
    ) { _ ->
        if (movie != null) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "More Details",
                    color = Color.White,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(550.dp)
                ) {
                    AsyncImage(
                        model = movie!!.poster,
                        contentDescription = null,
                        modifier = Modifier.matchParentSize()
                    )

                    Icon(
                        painter = painterResource(
                            id = if (isFavorite.value) R.drawable.baseline_favorite else R.drawable.outline_favorite_24
                        ),
                        tint = Color.Red,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(12.dp)
                            .align(Alignment.BottomEnd)
                            .width(40.dp) // حددي حجم واضح
                            .height(40.dp)
                            .clickable {
                                isFavorite.value = !isFavorite.value
                                if (isFavorite.value) {
                                    favViewModel.addToFavourites(movie!!)
                                } else {
                                    favViewModel.removeFromFavourites(movie!!)
                                }
                            }
                    )
                }


                Text(
                    text = movie!!.title,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .shadow(4.dp)
                )

                Text(text = "Year: ${movie!!.year}", color = Color.White)
                Text(text = "Type: ${movie!!.genres.joinToString()}", color = Color.White)
            }
        } else {
            Text("Loading...", color = Color.Gray)
        }
    }
}

//
//@Preview
//@Composable
//private fun DetailsScreenPreview() {
//    DetailsScreen(
//        movieId = 1,
//        navController = NavHostController(LocalContext.current),
//        viewModel = DetailsViewModel(),
//        favViewModel = FavouriteViewModel()
//    )
//}

