package com.example.movieapp

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieapp.ui.screens.DetailsScreen
import com.example.movieapp.ui.screens.FavouritesScreen
import com.example.movieapp.ui.screens.HomeScreen
import com.example.movieapp.viewmodel.FavouriteViewModel
import com.example.movieapp.viewmodel.HomeScreenViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Details : Screen("details/{id}") {
        fun passId(id: Int): String = "details/$id"
    }

    object WatchList : Screen("watchList")
}

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    val favViewModel: FavouriteViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            val viewModel: HomeScreenViewModel = viewModel()
            HomeScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("id") ?: 0
            DetailsScreen(
                movieId = movieId,
                navController = navController,
                favViewModel = favViewModel
            )
        }


        composable(Screen.WatchList.route) {
            FavouritesScreen(
                navController = navController,
                favViewModel = favViewModel
            )
        }
    }

}