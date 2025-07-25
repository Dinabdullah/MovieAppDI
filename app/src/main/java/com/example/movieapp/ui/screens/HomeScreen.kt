package com.example.movieapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.movieapp.R
import com.example.movieapp.Screen
import com.example.movieapp.ui.componants.BottomBar
import com.example.movieapp.ui.componants.MovieBox
import com.example.movieapp.viewmodel.HomeScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val movies by viewModel.movies.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.what_do_you_want_to_watch)) },
            )
        },
        bottomBar = {
            BottomBar(
                homeClicked = {
                    if (navController.currentDestination?.route != Screen.Home.route) {
                        navController.navigate(Screen.Home.route)
                    }
                },
                searchClicked = {
                },
                watchListClicked = {
                }
            )
        },
        containerColor = Color(0xFF242A32),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFF242A32)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            // Call the API for the first time
            LaunchedEffect(Unit) {
                viewModel.fetchMovies()
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.dp_6))
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy( dimensionResource(id = R.dimen.dp_12)),
                verticalArrangement = Arrangement.spacedBy( dimensionResource(id = R.dimen.dp_12)),
            ) {
                items(movies.size) { index ->
                    val movie = movies[index]
                    MovieBox(
                        "https://image.tmdb.org/t/p/w500${movie.poster_path}",
                        onClick = {
                        },
                        title = movie.title
                    )
                }
            }

        }
    }
}

