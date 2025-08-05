package com.example.movieapp.ui.screens.details

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.ui.components.NoInternet
import com.example.movieapp.ui.components.getFullPosterUrl
import com.example.movieapp.ui.screens.details.MovieDetailsEvents.FetchMovie

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailsScreen(
    modifier: Modifier = Modifier,
    state: MovieDetailsState,
    onDetailsEvents: (MovieDetailsEvents) -> Unit,
    movieId: Int,
    onNavigateToHome: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    LaunchedEffect(movieId) {
        onDetailsEvents(FetchMovie(movieId))
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { onNavigateToHome() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                title = { Text(text = stringResource(R.string.Movie_Details)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = Color.Black,
    ) { innerPadding ->

        when (val currentState = state) {
            is MovieDetailsState.OnError -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = currentState.msgRes),
                        color = Color.Red
                    )
                }
            }

            is MovieDetailsState.IsOffline -> {
                NoInternet(
                    modifier = Modifier.padding(innerPadding),
                    onRetry = {
                        onDetailsEvents(FetchMovie(movieId))
                    }

                )
            }

            is MovieDetailsState.OnSuccess -> {
                val movie = currentState.movie
                val scrollState = rememberScrollState()

                Column(
                    modifier = modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    AsyncImage(
                        model = movie.posterPath?.getFullPosterUrl(),
                        contentDescription = null,
                        modifier = Modifier
                            .sharedElement(
                                state = rememberSharedContentState(key = movie.id.toString()),
                                animatedVisibilityScope = animatedVisibilityScope,
                            )
                    )

                    Text(
                        text = movie.title ?: stringResource(R.string.unknown_title),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = dimensionResource(id = R.dimen.dp_24).value.sp,
                        modifier = Modifier
                            .padding(vertical = dimensionResource(id = R.dimen.dp_6))
                            .sharedElement(
                                state = rememberSharedContentState(key = movie.id.toString() + "title"),
                                animatedVisibilityScope = animatedVisibilityScope,
                            )
                    )

                    Text(
                        text = movie.overview ?: stringResource(R.string.no_overview_available),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = dimensionResource(id = R.dimen.dp_16).value.sp,
                        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.dp_6))
                    )
                }
            }

            MovieDetailsState.Idle -> Unit
        }
    }
}
