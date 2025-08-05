package com.example.movieapp.ui.screens.home

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
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
import com.example.movieapp.ui.components.BottomBar
import com.example.movieapp.ui.components.MovieBox
import com.example.movieapp.ui.components.NoInternet

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeScreen(
    modifier: Modifier = Modifier,
    state: UIState,
    onEvent: (Events) -> Unit,
    onNavigateToDetails: (Int) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
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
                homeClicked = {},
                searchClicked = {},
                watchListClicked = {}
            )
        },
        containerColor = Color.Black,
    ) { innerPadding ->
        when (state) {
            UIState.IsLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            UIState.IsOffline -> {
                NoInternet(
                    modifier = Modifier.padding(innerPadding),
                    onRetry = {
                        onEvent.invoke(Events.FetchMovies)
                    }
                )
            }

            is UIState.MoviesFetched -> {
                LazyVerticalGrid(
                    columns = Fixed(2),
                    modifier = modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp_12)),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dp_12)),
                ) {
                    items(state.list) { movie ->
                        MovieBox(
                            movie = movie,
                            onClick = {
                                movie.id?.let { id ->
                                    onNavigateToDetails(id)
                                }

                            },
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                    }
                }
            }

            is UIState.OnError -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(state.msg), color = Color.Red)
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp_12)))
                    Button(onClick = { onEvent(Events.FetchMovies) }) {
                        Text(stringResource(id = R.string.retry))
                    }
                }
            }

        }
    }
}


