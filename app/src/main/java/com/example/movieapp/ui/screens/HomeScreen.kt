package com.example.movieapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieapp.Screen
import com.example.movieapp.ui.componants.BottomBar
import com.example.movieapp.ui.componants.MovieBox
import com.example.movieapp.ui.componants.SearchBar
import com.example.movieapp.viewmodel.HomeScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: HomeScreenViewModel
) {
    val movies by viewModel.movies.collectAsState()
    var searchText by remember { mutableStateOf("") }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "What do you want to watch?") },
            )
        },
        bottomBar = {
            BottomBar(
                homeClicked = {
                    if (navController.currentDestination?.route != Screen.Home.route) {
                        navController.navigate(Screen.Home.route)
                    }
                },
                searchClicked = { navController.navigate(Screen.Details.route) },
                watchListClicked = { navController.navigate(Screen.WatchList.route) }
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

            SearchBar(
                search = searchText,
                onSearch = {
                    searchText = it
                    if (it.isNotEmpty()) {
                        viewModel.searchMovies(it)
                    } else {
                        viewModel.fetchMovies()
                    }
                }
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(movies) { movie ->
                    MovieBox(
                        movie.poster,
                        onClick = {
                            navController.navigate(Screen.Details.passId(movie.id))
                        },
                        title = movie.title
                    )
                }
            }

        }
    }
}


@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        navController = NavHostController(LocalContext.current),
        modifier = Modifier,
        viewModel = HomeScreenViewModel()
    )
}
