package com.example.movieapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.movieapp.R
import com.example.movieapp.ui.componants.BottomBar
import com.example.movieapp.ui.componants.MovieBox
import com.example.movieapp.viewmodel.Events
import com.example.movieapp.viewmodel.UIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: UIState,
    onEvent: (Events) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.what_do_you_want_to_watch)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomBar(
                homeClicked = {
//                    if (navController.currentDestination?.route != Screen.Home.route) {
//                        navController.navigate(Screen.Home.route)
//                    }
                },
                searchClicked = {
                },
                watchListClicked = {
                }
            )
        },
        containerColor = Color.Black,
    ) { innerPadding ->
        when {
            state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            state.isOffline -> {
                NoInternetScreen(
                    modifier = Modifier.padding(innerPadding),
                    onRetry = {
                        onEvent.invoke(Events.FetchMovies)
                    }
                )
            }

            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp_12)),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp_12)),
                ) {
                    items(state.movies) { movie ->
                        MovieBox(
                            movie = movie,
                            onClick = {
                            },

                            )
                    }
                }
            }
        }
    }
}

