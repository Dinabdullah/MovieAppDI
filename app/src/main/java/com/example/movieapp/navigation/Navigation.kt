package com.example.movieapp.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.movieapp.ui.screens.details.DetailsScreen
import com.example.movieapp.ui.screens.details.DetailsViewModel
import com.example.movieapp.ui.screens.home.HomeScreen
import com.example.movieapp.ui.screens.home.HomeScreenViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = Routes.Home,
        ) {
            composable<Routes.Home> {
                val viewModel: HomeScreenViewModel = koinViewModel()
                val state by viewModel.uiState.collectAsState()
                HomeScreen(
                    modifier = modifier,
                    state = state,
                    onEvent = viewModel::onEvent,
                    onNavigateToDetails = { id ->
                        navController.navigate(Routes.Detail(id))
                    },
                    animatedVisibilityScope = this
                )
            }

            composable<Routes.Detail> { backStackEntry ->
                val details = backStackEntry.toRoute<Routes.Detail>()
                val detailsViewModel: DetailsViewModel = koinViewModel()
                val detailsState by detailsViewModel.detailsState.collectAsState()
                DetailsScreen(
                    modifier = modifier,
                    movieId = details.movieId,
                    onNavigateToHome = {
                        navController.popBackStack()
                    },
                    animatedVisibilityScope = this,
                    state = detailsState,
                    onDetailsEvents = detailsViewModel::onEvent

                )
            }
        }
    }
}

